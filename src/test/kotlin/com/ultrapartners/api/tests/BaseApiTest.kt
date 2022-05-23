package com.ultrapartners.api.tests

import com.ultrapartners.api.tests.utils.useInsecureSSL


open class BaseApiTest {
    init {
        useInsecureSSL()
    }
}