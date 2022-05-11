package com.ultrapartners.api.tests.restassured

import com.ultrapartners.api.tests.common.AUTH_STATUS_URL
import com.ultrapartners.api.tests.common.LOGIN_URL
import com.ultrapartners.api.tests.common.LOGOUT_URL
import com.ultrapartners.api.tests.common.REFRESH_TOKEN_URL
import com.ultrapartners.api.tests.models.AuthTokenResponse
import com.ultrapartners.api.tests.models.LoginRequest
import com.ultrapartners.api.tests.models.LoginResponse
import com.ultrapartners.api.tests.utils.fromJson
import com.ultrapartners.ui.tests.Props
import io.qameta.allure.restassured.AllureRestAssured
import io.restassured.RestAssured.given
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.aeonbits.owner.ConfigFactory
import org.apache.http.HttpStatus.SC_OK
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test

class RestAssuredLoginTest {

    private val props = ConfigFactory.create(Props::class.java)

    private val requestSpecification: RequestSpecification = RequestSpecBuilder()
        .addFilter(AllureRestAssured())
        .setBaseUri(props.ultraPartnersUrl())
        .setContentType(ContentType.JSON)
        .setRelaxedHTTPSValidation()
        .log(LogDetail.ALL)
        .build()



    @Test
    fun loginTest() {
        val data = given(requestSpecification)
            .body(
                LoginRequest(props.login(), props.password())
            )
            .`when`()
            .post(LOGIN_URL)
            .then()
            .log().all()
            .assertThat()
            .statusCode(SC_OK)
            .body("data", notNullValue())
            .body("token", notNullValue())
            .body("refresh", notNullValue()).extract().asString().fromJson(LoginResponse::class.java)

        val newData = given(requestSpecification)
            .headers("Authorization", "Bearer ${data.token}", "Refresh", data.refresh)
            .`when`()
            .post(REFRESH_TOKEN_URL)
            .then()
            .log().all()
            .assertThat()
            .statusCode(SC_OK)
            .body("token", notNullValue())
            .body("refresh", notNullValue()).extract().asString().fromJson(AuthTokenResponse::class.java)

        given(requestSpecification)
            .header("Authorization", "Bearer ${newData.token}")
            .`when`()
            .get(AUTH_STATUS_URL)
            .then()
            .log().all()
            .assertThat()
            .statusCode(SC_OK)
            .body("logged", equalTo(true))
            .body("username", equalTo(props.login()))

        given(requestSpecification)
            .header("Authorization", "Bearer ${newData.token}")
            .`when`()
            .post(LOGOUT_URL)
            .then()
            .log().all()
            .assertThat()
            .statusCode(SC_OK)
            .body("logged", equalTo(false))
    }


}