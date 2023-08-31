package com.schedule.vote.controller;


import com.schedule.vote.model.Vote;
import com.schedule.vote.service.VoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public Optional<Vote> getVote(@PathVariable Long id){
        return voteService.getVote(id);

    }
}
