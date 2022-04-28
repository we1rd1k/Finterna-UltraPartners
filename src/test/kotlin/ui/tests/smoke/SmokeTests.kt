package ui.tests.smoke

import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel.CRITICAL
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import ui.tests.BaseTest
import ui.tests.Props
import ui.tests.data.SMOKE
import ui.tests.smoke.steps.`Affiliate Login`
import ui.tests.smoke.steps.`Affiliate Logout`
import ui.tests.smoke.steps.`Change password`
import ui.tests.smoke.steps.`Check availability of links`


@Tag(SMOKE)
@Epic("Affiliate Smoke")
@Severity(CRITICAL)
@DisplayName("Smoke Tests")
class SmokeTests : BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)


    @Feature("Login test")
    @DisplayName("[Affiliate][Authorization]Authorization check")
    @Severity(CRITICAL)
    @Test
    fun `Login test`() {
        `Affiliate Login`(props.login(), props.password())
    }

    @Feature("Logout test")
    @DisplayName("[Affiliate][Logout]")
    @Severity(CRITICAL)
    @Test
    fun `Logout test`() {
        `Affiliate Login`(props.login(), props.password())
        `Affiliate Logout`()
    }

    @DisplayName("[Affiliate][Sections]Check the availability of sections of the site")
    @Severity(CRITICAL)
    @Test
    fun `Availability of sections test`() {
        `Affiliate Login`(props.login(), props.password())
        `Check availability of links`()
    }

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

    @Disabled
    @DisplayName("[Affiliate][Settings][Billing Details]Adding payment details")
    @Severity(CRITICAL)
    @Test
    fun `Adding payment details test`() {
        `Affiliate Login`(props.login(), props.password())
        //TODO: test incomplete
    }
}