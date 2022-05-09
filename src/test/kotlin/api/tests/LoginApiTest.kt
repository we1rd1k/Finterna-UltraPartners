package api.tests

import api.tests.assertions.ANY_STRING
import api.tests.assertions.NOT_EMPTY
import api.tests.assertions.andVerifyResponseIs
import api.tests.common.LOGIN_URL
import api.tests.models.LoginRequest
import api.tests.models.LoginResponse
import api.tests.utils.post
import org.junit.jupiter.api.Test

class LoginApiTest: BaseApiTest() {

    @Test
    fun loginTest() {
        post(LOGIN_URL, LoginRequest("demo_admin_user", "111222"))
            .andVerifyResponseIs(200, LoginResponse(data = NOT_EMPTY, token = ANY_STRING, refresh = ANY_STRING))
    }
}