package com.schedule.vote.controller;


import com.schedule.vote.model.Vote;
import com.schedule.vote.service.VoteService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/{id}")
    public Vote getVote(@PathVariable Long id) {
        return voteService.getVote(id);
    }

    @PostMapping("/insert")
    public Vote createVote(@RequestBody Vote vote) {
        return voteService.createVote(vote);
    }
}
