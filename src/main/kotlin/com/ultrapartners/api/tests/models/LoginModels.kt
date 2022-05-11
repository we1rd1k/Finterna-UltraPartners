package com.ultrapartners.api.tests.models

data class LoginRequest(
    var email: String?,
    var password: String?
)
data class LoginResponse(
    var data: String,
    var token: String,
    var refresh: String
)

data class AuthTokenResponse(
    var token: String,
    var refresh: String
)

data class LoginStatusResponse(
    var logged: Boolean,
    var username: String
)

data class LogoutResponse(
    var logged: Boolean
)