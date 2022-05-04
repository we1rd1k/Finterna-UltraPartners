package com.ultrapartners.ui.tests.smoke

import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.smoke.steps.*
import com.ultrapartners.ui.tests.utils.generateRndEmail
import com.ultrapartners.ui.tests.utils.generateRndNumber
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel.CRITICAL
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import ui.tests.data.SMOKE


@Tag(SMOKE)
@Epic("Affiliate Smoke")
@Severity(CRITICAL)
@DisplayName("Smoke Tests")
class SmokeTests : BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)


    @Feature("Login")
    @DisplayName("[Affiliate][Authorization]Authorization check")
    @Severity(CRITICAL)
    @Test
    fun `Login test`() {
        `Affiliate Login`(props.login(), props.password())
    }

    @Feature("Logout")
    @DisplayName("[Affiliate][Logout]")
    @Severity(CRITICAL)
    @Test
    fun `Logout test`() {
        `Affiliate Login`(props.login(), props.password())
        `Affiliate Logout`()
    }

    @Feature("Availability of sections")
    @DisplayName("[Affiliate][Sections]Check the availability of sections of the site")
    @Severity(CRITICAL)
    @Test
    fun `Availability of sections test`() {
        `Affiliate Login`(props.login(), props.password())
        `Check availability of links`()
    }

    @Feature("Password change")
    @DisplayName("[Affiliate][Change password]")
    @Severity(CRITICAL)
    @Test
    fun `Password change test`() {
        val testPassword = "Test123"
        `Affiliate Login`(props.login(), props.password())
        `Change password`(props.password(), testPassword)
        `Affiliate Logout`()
        //Login with new password
        `Affiliate Login`(props.login(), testPassword)
        //Change password back to default
        `Change password`(testPassword, props.password())
    }

    @Feature("Adding payment details")
    @DisplayName("[Affiliate][Settings][Billing Details]Adding payment details")
    @Severity(CRITICAL)
    @Test
    fun `Adding payment details test`() {
        `Affiliate Login`(props.login(), props.password())
        `Add payment info`(neteller = generateRndEmail(), skrill = generateRndEmail(), ecoPayz = generateRndNumber())
    }

    @Feature("Registration fields")
    @DisplayName("[Affiliate][Registration]")
    @Severity(CRITICAL)
    @ParameterizedTest(name = "{displayName}, firstName: {0}, lastName: {1}, email: {2}, password: {3}")
    @MethodSource("regPositiveFields")
    fun `Registration fields positive test`(firstName: String, lastName: String, email: String, password: String) {
        `Check registration fields`(firstName, lastName, email, password)
    }

    companion object {
        @JvmStatic
        fun regPositiveFields() = listOf(
            of("Maxim", "Maxim", "nastusha@mail.ru", "testtest"),
            of("Лейла", "Лейла", "pro-tected@gmail.com", "123443213")
        )
    }
}