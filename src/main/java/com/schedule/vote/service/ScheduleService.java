package com.schedule.vote.service;

import static java.time.LocalDateTime.now;

import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;

import org.hibernate.ObjectNotFoundException;

import org.springframework.stereotype.Service;

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
        var scheduleResponse = scheduleRepository.findById(schedule.getId());
        var date = now();

        if (scheduleResponse.isPresent()) {
            //TODO: QUEBRAR EM UMA FUNÇÃO A PARTE A VALIDAÇÃO PARA VERIFICAR SE A PAUTA É PRESENTE.
            if (scheduleResponse.get().getDeadline() == null) {
                //TODO: QUEBRAR EM UMA OUTRA FUNÇÃO A PARTE DE VERIFICAR SE O PRAZO DA PAUTA DO BANCO DE DADOS É NULO.
                if (schedule.getDeadline() != null) {
                    //TODO: QUEBRAR EM UMA OUTRA FUNÇÃO A PARTE DE VERIFICAR SE O PRAZO DA PAUTA É NULO.
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
