package com.ultrapartners.api.tests

import com.ultrapartners.api.tests.apis.getAppHealthCheck
import com.ultrapartners.api.tests.apis.getNginxHealthCheck
import com.ultrapartners.api.tests.assertions.andVerifyResponseContains
import com.ultrapartners.api.tests.assertions.andVerifyResponseIs
import com.ultrapartners.api.tests.models.AppHealthResponse
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import ui.tests.data.HEALTH_CHECK

@Epic("Health check")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("App Health Check Tests")
@Tag(HEALTH_CHECK)
class AppHealthCheckTests: BaseApiTest() {

    @Feature("App basic Health check")
    @DisplayName("App basic Health Check Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun appHealthCheckTest() {
        getAppHealthCheck().andVerifyResponseIs(200, AppHealthResponse())
    }

    @Feature("Nginx Health check")
    @DisplayName("Nginx Health Check Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun nginxHealthCheck() {
        getNginxHealthCheck().andVerifyResponseContains(200, "healthy")
    }
}