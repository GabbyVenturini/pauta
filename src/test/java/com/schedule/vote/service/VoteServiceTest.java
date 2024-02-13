package com.schedule.vote.service;

import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.ScheduleRepository;
import com.schedule.vote.repository.VoteRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private VoteService voteService;

    @Test
    public void shouldReturnVote() {
        var vote = mock(Vote.class);

        given(vote.getId()).willReturn(1L);
        given(vote.getIdUser()).willReturn(1L);
        given(vote.getIdSchedule()).willReturn(1L);

        given(voteRepository.findById(1L)).willReturn(Optional.of(vote));

        var result = voteService.getVote(vote.getId());

        then(1L).equals(result.getId());
        then(1L).equals(result.getIdUser());
        then(1L).equals(result.getIdSchedule());

    }

    @Test
    public void shouldCreateVote() {
        var vote = mock(Vote.class);

        given(vote.getId()).willReturn(1L);
        given(vote.getIdUser()).willReturn(1L);
        given(vote.getIdSchedule()).willReturn(1L);

        given(voteRepository.save(vote)).willReturn(vote);

        var result = voteService.createVote(vote);

        assertNotNull(vote);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdUser());
        assertEquals(1L, result.getIdSchedule());
    }

    @Test
    public void shouldGetVoteError() {

        thenThrownBy(() -> voteService.getVote(1L))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldCreateVoteScheduleError() {
        var vote = mock(Vote.class);

        given(vote.getIdSchedule()).willReturn(null);

        thenThrownBy(() -> voteService.createVote(vote))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    public void shouldCreateUserVoteError() {
        var vote = mock(Vote.class);

        given(vote.getIdUser()).willReturn(null);

        thenThrownBy(() -> voteService.createVote(vote))
                .isInstanceOf(BadRequestException.class);
    }

    @Test
    public void shouldReturnVotingResultTrue() {
        var voteTrue = mock(Vote.class);
        var schedule = mock(Schedule.class);

        given(voteTrue.isVote()).willReturn(true);

        given(schedule.getDeadline()).willReturn(LocalDateTime.now());
        given(schedule.getId()).willReturn(1L);
        given(voteTrue.getIdSchedule()).willReturn(1L);

        given(scheduleRepository.findById(1L)).willReturn(Optional.ofNullable(schedule));
        given(voteRepository.findByIdSchedule(voteTrue.getIdSchedule())).willReturn(List.of(voteTrue));

        var resultVotation = voteService.getResult(voteTrue.getIdSchedule());

        assertEquals(1L, resultVotation.getScheduleId());
        assertEquals(1, resultVotation.getYes());
        assertEquals(0,resultVotation.getNo());
        assertEquals(1L, schedule.getId());
    }

    @Test
    public void shouldReturnVotingResultFalse() {
        var voteFalse = mock(Vote.class);
        var schedule = mock(Schedule.class);

        given(voteFalse.isVote()).willReturn(false);

        given(schedule.getDeadline()).willReturn(LocalDateTime.now());
        given(schedule.getId()).willReturn(1L);

        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(voteRepository.findByIdSchedule(schedule.getId())).willReturn(List.of(voteFalse));

        var resultVotation = voteService.getResult(schedule.getId());

        assertEquals(1L, resultVotation.getScheduleId());
        assertEquals(0, resultVotation.getYes());
        assertEquals(1,resultVotation.getNo());
        assertEquals(1L, schedule.getId());
    }

    @Test
    public void shouldReturnErrorVoting(){
        var schedule = mock(Schedule.class);

        given(scheduleRepository.findById(1L)).willReturn(Optional.ofNullable(schedule));
        given(schedule.getDeadline()).willReturn(LocalDateTime.now().plusMinutes(1));

        thenThrownBy(()-> voteService.getResult(1L))
                .isInstanceOf(ForbiddenException.class);

    }
}