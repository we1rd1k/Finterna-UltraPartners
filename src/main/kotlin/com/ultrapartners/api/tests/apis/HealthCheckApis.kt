package com.ultrapartners.api.tests.apis

import com.ultrapartners.api.tests.common.APP_HEALTH_CHECK_URL
import com.ultrapartners.api.tests.common.NGINX_HEALTH_CHECK_URL
import com.ultrapartners.api.tests.utils.get

fun getAppHealthCheck() = get<String>(APP_HEALTH_CHECK_URL)

fun getNginxHealthCheck() = get<String>(NGINX_HEALTH_CHECK_URL)