package com.schedule.vote.service;

import com.schedule.vote.model.User;
import com.schedule.vote.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
    public Optional<User> getSchedule(Long id){
        return scheduleRepository.findById(id); }
}
