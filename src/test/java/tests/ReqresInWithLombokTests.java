package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.LoginBodyModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@Tag("LombokTests")
public class ReqresInWithLombokTests {

    @Test
    @DisplayName("Checking login successful")
    void LoginSuccessfulTest() {
        LoginBodyModel loginBody = new LoginBodyModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseModel loginResponse =
                step("Get login data", () ->
                    given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .contentType(JSON)
                        .body(loginBody)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));

        step("Verify login response", () ->
                assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));

    }

    @Test
    @DisplayName("Checking login unsuccessful")
    void LoginErrorTest() {
        LoginBodyModel loginBody = new LoginBodyModel();
        loginBody.setEmail("testEmail@test");
        loginBody.setPassword("testEmailtest");

        given()
                .filter(withCustomTemplates())
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(loginBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("user not found"));
    }

}

