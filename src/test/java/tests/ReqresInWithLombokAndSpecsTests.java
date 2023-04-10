package tests;

import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;
import static specs.Spec.*;

@Tag("LombokTests")
public class ReqresInWithLombokAndSpecsTests {

    @Test
    @DisplayName("Check that status code of request of list existing users is 200 OK")
    void firstNameInSingleUserLombokTest() {
            step("Check that status code of request of list existing users is 200 OK", () ->
                given(requestSpec)
                    .when()
                    .get("/users/2")
                    .then()
                    .spec(responseSpecWithCode200));
    }

    @Test
    @DisplayName("Check that status code of request of non-existent resource is 404 Not Found ")
    void resourceNotFoundLombokTest() {
            step("Check that status code of request of non-existent resources is 404 Not Found", () ->
                given(requestSpec)
                    .when()
                    .get("/unknown/23")
                    .then()
                    .spec(responseSpecWithCode404));
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

