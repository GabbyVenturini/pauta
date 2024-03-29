package com.schedule.vote.controller;


import com.schedule.vote.dto.ResultVotation;
import com.schedule.vote.model.Vote;
import com.schedule.vote.service.VoteService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/result/{id}")
    public ResultVotation getResult(@PathVariable Long id){
        return voteService.getResult(id);
    }

}
