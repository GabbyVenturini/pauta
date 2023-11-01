package com.schedule.vote.service;

import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.model.User;
import com.schedule.vote.repository.ScheduleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.io.ObjectStreamException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

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

    public Schedule insertSession(Schedule schedule) {
        Optional<Schedule> scheduleResponse = scheduleRepository.findById(schedule.getId());
        var date = LocalDateTime.now();

        if (scheduleResponse.isPresent()) {
            if (scheduleResponse.get().getDeadline() == null) {
                if (schedule.getDeadline() != null) {
                    scheduleResponse.get().setDeadline(schedule.getDeadline());
                } else {
                    var newDeadLine = date.plusMinutes(1);
                    scheduleResponse.get().setDeadline(newDeadLine);
                }
                return scheduleRepository.save(scheduleResponse.get());
            }
            throw new ForbiddenException("Pauta fechada, impossível criar outra sessao.");
        }
        throw new ObjectNotFoundException(schedule.getId(), Schedule.class.getSimpleName());
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
