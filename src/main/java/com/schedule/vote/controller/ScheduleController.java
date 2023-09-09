package com.schedule.vote.controller;

import com.schedule.vote.model.Schedule;
import com.schedule.vote.model.User;
import com.schedule.vote.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    @GetMapping("/{id}")
    public Optional<Schedule> getSchedule(@PathVariable Long id){
        return scheduleService.getSchedule(id);

    }

    @PostMapping("/insert")
    public Schedule createSchedule(@RequestBody Schedule schedule){
        return scheduleService.createSchedule(schedule);
    }
}
