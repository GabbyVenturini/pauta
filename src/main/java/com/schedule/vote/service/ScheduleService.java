package com.schedule.vote.service;

import com.schedule.vote.model.Schedule;
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

    public Optional<Schedule> getSchedule(Long id){
        return scheduleRepository.findById(id); }

    public Schedule createSchedule(Schedule schedule){

        return scheduleRepository.save(schedule);
    }
}
