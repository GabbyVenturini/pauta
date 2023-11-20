package com.schedule.vote.service;

import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.VoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private VoteService voteService;

    @Test
    public void shouldReturnVote() {
        var vote = new Vote();
        vote.setId(1L);
        vote.setIdUser(1L);
        vote.setIdSchedule(1L);

        given(voteRepository.findById(1L)).willReturn(Optional.of(vote));

        var result = voteService.getVote(vote.getId());

        then(result.getId()).equals(1L);
        then(result.getIdUser()).equals(1L);
        then(result.getIdSchedule()).equals(1L);

    }

    @Test
    public void shouldCreateVote() {
        var vote = new Vote();
        vote.setId(1L);
        vote.setIdUser(1L);
        vote.setIdSchedule(1L);

        given(voteRepository.save(vote)).willReturn(vote);

        Vote result = voteService.createVote(vote);

        assertNotNull(vote);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getIdUser());
        assertEquals(1L, result.getIdSchedule());
    }
}
