package api.tests.models

data class LoginRequest(
    var email: String?,
    var password: String?
)
data class LoginResponse(
    var data: String,
    var token: String,
    var refresh: String
)