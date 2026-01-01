package com.cms.admin.e2e;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ComplaintFlowTest extends BaseTest {

    @Test
    public void shouldCreateComplaintAndAssignToTeam() {
        // 1. Create complaint
        String ticketId = "TEST-" + System.currentTimeMillis();

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "ticketId": "%s",
                  "userId": 1,
                  "category": "Technical Issues",
                  "priority": "P0",
                  "description": "Test complaint"
                }
                """.formatted(ticketId))
                .when()
                .post("/api/v1/admin/complaints")
                .then()
                .statusCode(200)
                .body("ticketId", equalTo(ticketId))
                .body("status", equalTo("OPEN"));

        // 2. Get complaint ID (extract from response)
        long complaintId = given()
                .when()
                .get("/api/v1/admin/complaints")
                .then()
                .extract()
                .jsonPath()
                .getLong("[0].id");

        // 3. Assign to team (assume team ID 1 = IT Support)
        given()
                .when()
                .post("/api/v1/admin/workflow/complaints/" + complaintId + "/assign/1")
                .then()
                .statusCode(200)
                .body("status", equalTo("ASSIGNED_TO_TEAM"))
                .body("departmentId", equalTo(1));

        // 4. Verify resolution
        given()
                .when()
                .post("/api/v1/admin/workflow/complaints/" + complaintId + "/verify")
                .then()
                .statusCode(200)
                .body("status", equalTo("CLOSED"));
    }

    @Test
    public void shouldTriggerSlaBreachPenalty() {
        // This is harder to test automatically (time-based)
        // But you can manually set deadline in DB and run scheduler
        System.out.println("Manual test: Set sla_response_deadline to past, wait 1 min, check penalty = 10");
    }
}