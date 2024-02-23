package com.schedule.vote.utils;

import io.restassured.RestAssured;

public class RequestSpec {

    public static void on(int port){
        RestAssured.port = port;
    }
}
