package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static specs.Spec.*;

public class ReqresInWithGroovyTests {

    @Test
    @DisplayName("Check that response body has email")
    @Tag("GroovyTests")
    void firstNameInSingleUserLombokTest() {
            step("Check that response body has email", () ->
                given(requestSpec)
                    .when()
                    .get("/users?page=2")
                    .then()
                    .spec(responseSpecWithCode200))
                    .body("data.find{it.id == 10}.email", is("byron.fields@reqres.in"))
                    .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                            hasItem("george.edwards@reqres.in"))
                    .body("data.findAll{it}.id.flatten()", hasItem(10));
    }

    @Test
    @DisplayName("Check login unsuccessful")
    void loginErrorLombokTest() {
        LoginBodyModel loginBody = new LoginBodyModel();
        loginBody.setEmail("testEmail@test");
        loginBody.setPassword("Emailtest");

        LoginResponseErrorModel loginResponseError =
                step("Get login data", () ->
                        given(requestSpec)
                                .body(loginBody)
                                .when()
                                .post("/login")
                                .then()
                                .spec(responseSpecWithCode400)
                                .extract().as(LoginResponseErrorModel.class));

        step("Verify login response", () ->
                assertThat(loginResponseError.getError()).isEqualTo("user not found"));
    }

    @Test
    @DisplayName("Check login successful")
    void loginSuccessfulLombokTest() {
        LoginBodyModel loginBody = new LoginBodyModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseModel loginResponse =
                step("Get login data", () ->
                        given(requestSpec)
                            .body(loginBody)
                            .when()
                            .post("/login")
                            .then()
                            .spec(loginResponseSpec)
                            .extract().as(LoginResponseModel.class));

        step("Verify login response", () ->
                assertThat(loginResponse.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Check update user")
    void updateUserLombokTest() {
        UpdateBodyModel updateBody = new UpdateBodyModel();
        updateBody.setName("morpheus");
        updateBody.setJob("zion resident");

        UpdateResponseModel updateResponse =
                step("Post update data", () ->
                        given(requestSpec)
                                .body(updateBody)
                                .when()
                                .put("/users/2")
                                .then()
                                .spec(updateResponseSpec)
                                .extract().as(UpdateResponseModel.class));

        step("Verify update response", () -> {
            assertThat(updateResponse.getName()).isEqualTo("morpheus");
            assertThat(updateResponse.getJob()).isEqualTo("zion resident");
            assertThat(updateResponse.getUpdatedAt()).isNotNull();
        });
    }
}

