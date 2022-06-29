package com.ultrapartners.api.tests.apis

import com.ultrapartners.api.tests.common.*
import com.ultrapartners.api.tests.models.EmptyRequest
import com.ultrapartners.api.tests.models.LoginRequest
import com.ultrapartners.api.tests.utils.post
import com.ultrapartners.api.tests.utils.signedGet
import com.ultrapartners.api.tests.utils.signedPost
import org.apiguardian.api.API
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun login(login: String, password: String) = post(LOGIN_URL, LoginRequest(login, password))

fun receiveTokens(accessToken: String, refreshToken: String) =
    signedPost(REFRESH_TOKEN_URL, EmptyRequest(), accessToken, headers = arrayOf("Refresh" to refreshToken))

fun getAuthStatus(accessToken: String) = signedGet(AUTH_STATUS_URL, accessToken)

fun logout(accessToken: String) = signedPost(LOGOUT_URL, EmptyRequest(), accessToken)

fun getAuthToken(username: String, password: String) =
    post(
        path = ACCESS_TOKEN_URL,
        headers = mapOf("Content-Type" to "application/x-www-form-urlencoded"),
        parameters = listOf("username" to username, "password" to password),
        request = null
    )

fun reportRequest(
    reportType: String,
    token: String,
    request: Any? = null,
    fromDate: LocalDateTime,
    toDate: LocalDateTime
) =
    signedPost(
        path = getReportPathBy(reportType),
        headers = arrayOf(Pair("Content-Type", "application/x-www-form-urlencoded")),
        token = token,
        request = request,
        parameters = listOf(
            "fromDate" to fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            "toDate" to toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )
    )
