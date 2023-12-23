package com.schedule.vote.validation;

import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;
import java.util.Optional;

import static java.time.LocalDateTime.now;

public class ScheduleValidator {

  private final ScheduleRepository scheduleRepository;

  public ScheduleValidator(ScheduleRepository scheduleRepository) {
    this.scheduleRepository = scheduleRepository;
  }

  public Schedule checkIfDeadlineIsNull(Schedule schedule) {
    var scheduleResponse = scheduleRepository.findById(schedule.getId());

    if (scheduleResponse.get().getDeadline() == null) {
      scheduleResponse.get().setDeadline(schedule.getDeadline());
    } else {
      setDeadlineSession(scheduleResponse);
    }
    throw new ForbiddenException("Pauta fechada, impossível criar outra sessao.");
  }

  public Schedule checkIfDeadlineIsDifferentFromNull(Schedule schedule) {
    var scheduleResponse = scheduleRepository.findById(schedule.getId());

    if (schedule.getDeadline() != null) {
      scheduleResponse.get().setDeadline(schedule.getDeadline());
    } else {
      setDeadlineSession(scheduleResponse);
    }
    throw new ForbiddenException("Pauta fechada, impossível criar outra sessao.");
  }

  public Optional<Schedule> setDeadlineSession(Optional<Schedule> schedule){
    var date = now();
    var newDeadLine = date.plusMinutes(1);

    schedule.get().setDeadline(newDeadLine);

    return schedule;
  }
}
