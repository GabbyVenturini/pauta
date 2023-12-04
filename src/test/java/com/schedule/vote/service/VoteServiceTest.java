package com.schedule.vote.service;

import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.VoteRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
    public void shouldGetVoteError(){
        var vote = mock(Vote.class);
        given(vote.getId()).willReturn(1L);
        thenThrownBy(()-> voteService.getVote(1L))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldCreateVoteScheduleError(){
        var vote = mock(Vote.class);
        given(vote.getIdSchedule()).willReturn(null);

        thenThrownBy(()->voteService.createVote(vote))
                .isInstanceOf(BadRequestException.class);

    }
    @Test
    public void shouldCreateUserVoteError(){
        var vote = mock(Vote.class);
        given(vote.getIdUser()).willReturn(null);

        thenThrownBy(()->voteService.createVote(vote))
                .isInstanceOf(BadRequestException.class);
    }
}
