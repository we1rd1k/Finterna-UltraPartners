package com.ultrapartners.api.tests.common

//Auth URLs
const val LOGIN_URL = "/main/login"
const val REFRESH_TOKEN_URL = "/auth/refresh"
const val AUTH_STATUS_URL = "/auth/status"
const val LOGOUT_URL = "/auth/logout"

//Health Check
const val APP_HEALTH_CHECK_URL = "/health"
const val NGINX_HEALTH_CHECK_URL = "/health/nginx"

//Reports API
const val API_V1_URL = "/api/v1"
const val ACCESS_TOKEN_URL = "$API_V1_URL/accessToken"
const val REPORT_URL = "$API_V1_URL/report"
