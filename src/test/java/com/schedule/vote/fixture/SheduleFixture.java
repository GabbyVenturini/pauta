package com.schedule.vote.fixture;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class SheduleFixture {
    public static Response getSchedule(Object id){
        return given()
                .basePath("/schedule")
                .pathParam("id", id)
                .when()
                .get("/{id}");
    }
}
