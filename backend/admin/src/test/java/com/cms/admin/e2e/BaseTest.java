package com.cms.admin.e2e;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "http://localhost:8081";
    }
}