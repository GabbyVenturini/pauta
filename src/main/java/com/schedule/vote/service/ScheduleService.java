package com.schedule.vote.service;

import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import static java.time.LocalDateTime.now;

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

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule insertSession(Schedule schedule) {
        var scheduleResponse = scheduleRepository.findById(schedule.getId());

        if (scheduleResponse.isPresent()) {
            checkIfDeadlineIsNull(schedule);
            checkIfDeadlineIsDifferentFromNull(schedule);
            return scheduleRepository.save(scheduleResponse.get());
        } else {
            throw new ObjectNotFoundException(schedule.getId(), Schedule.class.getSimpleName());
        }
    }

    public Schedule checkIfDeadlineIsNull(Schedule schedule) {
        var scheduleResponse = scheduleRepository.findById(schedule.getId());
        var date = now();

        if (scheduleResponse.get().getDeadline() == null) {
            scheduleResponse.get().setDeadline(schedule.getDeadline());
        } else {
            var newDeadLine = date.plusMinutes(1);
            scheduleResponse.get().setDeadline(newDeadLine);
        }
        throw new ForbiddenException("Pauta fechada, impossível criar outra sessao.");
    }

    public Schedule checkIfDeadlineIsDifferentFromNull(Schedule schedule) {
        var scheduleResponse = scheduleRepository.findById(schedule.getId());
        var date = now();

        if (schedule.getDeadline() != null) {
            scheduleResponse.get().setDeadline(schedule.getDeadline());
        } else {
            var newDeadLine = date.plusMinutes(1);
            scheduleResponse.get().setDeadline(newDeadLine);
        }
        throw new ForbiddenException("Pauta fechada, impossível criar outra sessao.");
    }
}