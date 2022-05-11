package com.ultrapartners.api.tests.apis

import com.ultrapartners.api.tests.common.AUTH_STATUS_URL
import com.ultrapartners.api.tests.common.LOGIN_URL
import com.ultrapartners.api.tests.common.LOGOUT_URL
import com.ultrapartners.api.tests.common.REFRESH_TOKEN_URL
import com.ultrapartners.api.tests.models.EmptyRequest
import com.ultrapartners.api.tests.models.LoginRequest
import com.ultrapartners.api.tests.utils.post
import com.ultrapartners.api.tests.utils.signedGet
import com.ultrapartners.api.tests.utils.signedPost


fun login(login: String, password: String) = post(LOGIN_URL, LoginRequest(login, password))

fun receiveTokens(accessToken: String, refreshToken: String) = signedPost(REFRESH_TOKEN_URL, EmptyRequest(), accessToken, headers = arrayOf("Refresh" to refreshToken))

fun getAuthStatus(accessToken: String) = signedGet(AUTH_STATUS_URL, accessToken)

fun logout(accessToken: String) = signedPost(LOGOUT_URL, EmptyRequest(), accessToken)