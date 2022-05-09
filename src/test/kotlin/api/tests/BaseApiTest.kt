package api.tests

import api.tests.utils.useInsecureSSL


open class BaseApiTest {
    init {
        useInsecureSSL()
    }
}