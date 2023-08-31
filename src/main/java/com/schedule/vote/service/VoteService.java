package com.schedule.vote.service;

import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.VoteRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class VoteService {

    private VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Optional<Vote> getVote(Long id) {
        return voteRepository.findById(id);
    }
}
