package com.schedule.vote.validation;

import static java.time.LocalDateTime.now;

import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleValidator {

  @Autowired
  private ScheduleRepository scheduleRepository;

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
      //TODO: QUEBRAR EM UMA OUTRA FUNÇÃO A LÓGICA ABAIXO PARA SETAR O PRAZO, ASSIM VOCÊ CONSEGUE USAR A MESMA LÓGICA PARA AS DUAS FUNÇOES.
      var newDeadLine = date.plusMinutes(1);
      scheduleResponse.get().setDeadline(newDeadLine);
    }
    throw new ForbiddenException("Pauta fechada, impossível criar outra sessao.");
  }
}
