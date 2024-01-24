package com.schedule.vote.service;

import com.schedule.vote.dto.ResultVotation;
import com.schedule.vote.exceptions.BadRequestException;
import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Vote;
import com.schedule.vote.repository.ScheduleRepository;
import com.schedule.vote.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class VoteService {

    private VoteRepository voteRepository;
    private ScheduleRepository scheduleRepository;

    public Vote getVote(Long id) {
        var vote = voteRepository.findById(id);
        if (vote.isPresent()) {
            return vote.get();
        } else {
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

    public ResultVotation getResult(Long idSchedule){
        var votes = voteRepository.findByIdSchedule(idSchedule);
        var schedule = scheduleRepository.findById(idSchedule);
        var date = LocalDateTime.now();

        if(date.isBefore(schedule.get().getDeadline())){
            throw new ForbiddenException("Votacao em andamento.");
        }

        var yes = 0;
        var no = 0;
        var result = "resultado";

        for(Vote vote : votes){
            if(vote.isVote()){
                yes++;
            }else{
                no++;
            }
        }
        if(yes > no){
            result = "Schedule approved";
        }else{
            result =  "Schedule disaproved";
        }
        return new ResultVotation(idSchedule, yes, no, result);
    }
}

