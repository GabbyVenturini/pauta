package com.schedule.vote.controller;

import com.schedule.vote.config.IntegrationTest;
import org.junit.Test;

import static com.schedule.vote.fixture.SheduleFixture.getSchedule;

public class ScheduleControllerIT extends IntegrationTest {

    @Test
    public void getScheduleById(){
        getSchedule(1L)
                .then()
                .statusCode(200);
    }
}
