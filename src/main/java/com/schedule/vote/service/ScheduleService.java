package com.schedule.vote.service;

import com.schedule.vote.model.Schedule;
import com.schedule.vote.model.User;
import com.schedule.vote.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule getSchedule(Long id) {
        var schedule = scheduleRepository.findById(id);
        if(schedule.isPresent()){
            return schedule.get();
        }
        throw new RuntimeException("Pauta inexistente na base de dados.");
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id){
        scheduleRepository.deleteById(id);
    }
}
