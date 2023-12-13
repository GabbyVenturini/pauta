package com.schedule.vote.service;

import com.schedule.vote.exceptions.ForbiddenException;
import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    public void shouldReturnSchedule() {
        var schedule = mock(Schedule.class);
        var date = now();

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(date);
        given(schedule.getDescription()).willReturn("Pauta sobre lider.");

        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        var result = scheduleService.getSchedule(schedule.getId());

        then(result.getId()).equals(1L);
        then(result.getDeadline()).equals(date);
        then(result.getDescription()).equals("Pauta sobre lider.");
    }

    @Test
    public void shouldCreateSchedule() {
        var schedule = mock(Schedule.class);
        var date = now();

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(date);
        given(schedule.getDescription()).willReturn("Pauta sobre lider.");

        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.createSchedule(schedule);

        assertNotNull(schedule);
        assertEquals(1L, result.getId());
        assertEquals(date, result.getDeadline());
        assertEquals("Pauta sobre lider.", result.getDescription());
    }

    @Test
    public void shouldInsertSession() {
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDescription()).willReturn("description");

        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));
        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.insertSession(schedule);

        then(result.getId()).equals(1L);
        then(result.getDeadline()).equals(now());
        then(result.getDescription()).equals("description");
    }

    @Test
    public void shouldDeleteSchedule() {
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(1L);

        scheduleService.deleteSchedule(schedule.getId());

        verify(scheduleRepository).deleteById(schedule.getId());
    }

    @Test
    public void shouldSearchScheduleError() {
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(null);

        thenThrownBy(() -> scheduleService.getSchedule(schedule.getId()))
                .isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    public void shouldInsertSessionError() {
        var schedule = mock(Schedule.class);

        given(schedule.getId()).willReturn(1L);
        given(schedule.getDeadline()).willReturn(now());
        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        thenThrownBy(() -> scheduleService.insertSession(schedule))
                .isInstanceOf(ForbiddenException.class);
    }
}