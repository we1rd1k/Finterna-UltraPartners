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
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.Test

class LoginApiTest: BaseApiTest() {

    private val props = ConfigFactory.create(Props::class.java)

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