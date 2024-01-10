package com.schedule.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultVotation {
    private Long scheduleId;
    private int yes;
    private int no;
    private String result;
}
