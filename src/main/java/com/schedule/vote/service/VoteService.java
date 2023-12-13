package com.schedule.vote.service;

import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;

    public Vote getVote(Long id) {
        var vote = voteRepository.findById(id);
        if(vote.isPresent()){
            return vote.get();
        }else{
            throw new ObjectNotFoundException(id, Vote.class.getSimpleName());
        }
    }

    public Vote createVote(Vote vote) {
        if (vote.getIdSchedule() == null) {
            throw new BadRequestException("Pauta incorreta.");
        }
        if (vote.getIdUser() == null) {
            throw new BadRequestException("Usuário incorreto.");
        } else {
            return voteRepository.save(vote);
        }
    }
}
