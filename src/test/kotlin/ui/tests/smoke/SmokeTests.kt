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


@Tag(SMOKE)
@Epic("Affiliate Smoke")
@Severity(CRITICAL)
@DisplayName("Smoke Tests")
class SmokeTests : BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)


    @Feature("Login test")
    @DisplayName("Login test")
    @Severity(CRITICAL)
    @Test
    fun `Login test`() {
        `Affiliate Login`(props.login(), props.password())
    }
    @Disabled
    @Feature("Logout test")
    @DisplayName("Logout test")
    @Severity(CRITICAL)
    @Test
    fun `Logout test`() {
        `Affiliate Login`(props.login(), props.password())
        `Affiliate Logout`()
    }
}