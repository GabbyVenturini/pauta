package com.schedule.vote.service;

import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;
import com.schedule.vote.validation.ScheduleValidator;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ScheduleValidator validator;

    public Schedule getSchedule(Long id) {
        var schedule = scheduleRepository.findById(id);
        if (schedule.isPresent()) {
            return schedule.get();
        }
        throw new ObjectNotFoundException(id, Schedule.class.getSimpleName());
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule insertSession(Schedule schedule) {
        var scheduleResponse = scheduleRepository.findById(schedule.getId());

        if (scheduleResponse.isPresent()) {
            validator.checkIfDeadlineIsNull(schedule);
            validator.checkIfDeadlineIsDifferentFromNull(schedule);
            return scheduleRepository.save(scheduleResponse.get());
        } else {
            throw new ObjectNotFoundException(schedule.getId(), Schedule.class.getSimpleName());
        }
    }
}