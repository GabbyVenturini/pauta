package com.schedule.vote.service;

import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.VoteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote getVote(Long id) {
        var vote = voteRepository.findById(id);
        if(vote.isPresent()){
            return vote.get();
        }else{
            throw new ObjectNotFoundException(id, Vote.class.getSimpleName());
        }
    }

    public Vote createVote(Vote vote) {
        if(vote.getIdUser() != null && vote.getIdSchedule() != null) {
            return voteRepository.save(vote);
        }else{
            throw new BadRequestException("Voto nao pode ser nulo.");
        }
    }
}
