package com.ultrapartners.api.tests

import com.ultrapartners.api.tests.apis.getAuthStatus
import com.ultrapartners.api.tests.apis.login
import com.ultrapartners.api.tests.apis.logout
import com.ultrapartners.api.tests.apis.receiveTokens
import com.ultrapartners.api.tests.assertions.ANY_STRING
import com.ultrapartners.api.tests.assertions.andVerifyResponseIs
import com.ultrapartners.api.tests.models.AuthTokenResponse
import com.ultrapartners.api.tests.models.LoginResponse
import com.ultrapartners.api.tests.models.LoginStatusResponse
import com.ultrapartners.api.tests.models.LogoutResponse
import com.ultrapartners.ui.tests.Props
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import ui.tests.data.API

@Epic("Auth API")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Auth API Tests")
@Tag(API)
class LoginApiTest: BaseApiTest() {

    private val props = ConfigFactory.create(Props::class.java)

    @Feature("Auth API")
    @DisplayName("[API] [Authorization] API Auth tests")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun loginTest() {
        val tokens = login(props.login(), props.password())
            .andVerifyResponseIs(200, LoginResponse(data = ANY_STRING, token = ANY_STRING, refresh = ANY_STRING))
        val newTokens = receiveTokens(tokens.token, tokens.refresh)
            .andVerifyResponseIs(200, AuthTokenResponse(token = ANY_STRING, refresh = ANY_STRING))
        getAuthStatus(newTokens.token)
            .andVerifyResponseIs(200, LoginStatusResponse(logged = true, username = props.login()))
        logout(newTokens.token)
            .andVerifyResponseIs(200,  LogoutResponse(logged = false))
    }
}