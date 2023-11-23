package com.schedule.vote.service;

import com.schedule.vote.model.Schedule;
import com.schedule.vote.repository.ScheduleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    public void shouldReturnSchedule() {
        var schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDeadline(LocalDateTime.parse("20-11-2023"));
        schedule.setDescription("Pauta sobre lider.");

        given(scheduleRepository.findById(1L)).willReturn(Optional.of(schedule));

        var result = scheduleService.getSchedule(schedule.getId());

        then(result.getId()).equals(1L);
        then(result.getDeadline()).equals("20-11-2023");
        then(result.getDescription()).equals("Pauta sobre lider.");
    }

    @Test
    public void shouldCreateSchedule() {
        var schedule = new Schedule();
        schedule.setId(1L);
        schedule.setDeadline(LocalDateTime.parse("20-11-2023"));
        schedule.setDescription("Pauta sobre lider.");

        given(scheduleRepository.save(schedule)).willReturn(schedule);

        var result = scheduleService.createSchedule(schedule);

        assertNotNull(schedule);
        assertEquals(1L, result.getId());
        assertEquals("20-11-2023", result.getDeadline());
        assertEquals("Pauta sobre lider.", result.getDescription());
    }

    @Test
    public void shouldIsertSession(){
        var schedule = new Schedule();
        schedule.setId(1L);
    }

    @Test
    public void shouldDeleteSession(){
        var schedule = new Schedule();
        schedule.setId(1L);
    }
}
