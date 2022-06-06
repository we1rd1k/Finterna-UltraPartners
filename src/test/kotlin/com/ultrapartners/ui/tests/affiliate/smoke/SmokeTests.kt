package com.ultrapartners.ui.tests.affiliate.smoke

import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.affiliate.smoke.steps.*
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
import org.junit.jupiter.params.provider.CsvFileSource
import ui.tests.data.CI
import ui.tests.data.SMOKE

@Tag(CI)
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
        `Affiliate Login`(props.affiliateLogin(), props.password())
    }

    @Feature("Logout")
    @DisplayName("[Affiliate][Logout]")
    @Severity(CRITICAL)
    @Test
    fun `Logout test`() {
        `Affiliate Login`(props.affiliateLogin(), props.password())
        `Affiliate Logout`()
    }

    @Feature("Availability of sections")
    @DisplayName("[Affiliate][Sections]Check the availability of sections of the site")
    @Severity(CRITICAL)
    @Test
    fun `Availability of sections test`() {
        `Affiliate Login`(props.affiliateLogin(), props.password())
        `Check availability of links`()
    }

    @Feature("Password change")
    @DisplayName("[Affiliate][Change password]")
    @Severity(CRITICAL)
    @Test
    fun `Password change test`() {
        val testPassword = "Test123"
        `Affiliate Login`(props.affiliateLogin(), props.password())
        `Change password`(props.password(), testPassword)
        `Affiliate Logout`()
        //Login with new password
        `Affiliate Login`(props.affiliateLogin(), testPassword)
        //Change password back to default
        `Change password`(testPassword, props.password())
    }

    @Feature("Adding payment details")
    @DisplayName("[Affiliate][Settings][Billing Details]Adding payment details")
    @Severity(CRITICAL)
    @Test
    fun `Adding payment details test`() {
        `Affiliate Login`(props.affiliateLogin(), props.password())
        `Add payment info`(neteller = generateRndEmail(), skrill = generateRndEmail(), ecoPayz = generateRndNumber())
    }

    @Feature("Registration fields")
    @DisplayName("[Affiliate][Registration][Positive]")
    @Severity(CRITICAL)
    @ParameterizedTest(name = "{displayName}, firstName: {0}, lastName: {1}, email: {2}, password: {3}")
    @CsvFileSource(resources = ["/registrationFields/positive.csv"])
    fun `Registration fields positive test`(firstName: String, lastName: String, email: String, password: String) {
        `Check registration fields`(firstName, lastName, email, password)
    }

    @Feature("Registration fields")
    @DisplayName("[Affiliate][Registration][Negative]")
    @Severity(CRITICAL)
    @ParameterizedTest(name = "{displayName}, email: {0}, password: {1}")
    @CsvFileSource(resources = ["/registrationFields/negative.csv"])
    fun `Registration fields negative test`(email: String, password: String) {
        `Check registration fields - negative`(email, password)
    }
}