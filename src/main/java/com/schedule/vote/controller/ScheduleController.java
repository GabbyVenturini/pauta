package com.schedule.vote.controller;

import com.schedule.vote.model.User;
import com.schedule.vote.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

    @RestController
    @RequestMapping("/schedule")
    public class ScheduleController{

        private ScheduleService scheduleService;

        public ScheduleController(ScheduleService scheduleService) {this.scheduleService = scheduleService;}

        @GetMapping("/{id}")
        public Optional<User> getSchedule(@PathVariable Long id){
            return scheduleService.getSchedule(id);

        }
    }
