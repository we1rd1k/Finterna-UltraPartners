package ui.tests

import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel.CRITICAL
import io.qameta.allure.Step
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import ui.tests.data.SMOKE
import ui.tests.pages.MainPage


@Tag(SMOKE)
@Epic("Affiliate Smoke")
@Severity(CRITICAL)
@DisplayName("Login Test")
class LoginTest : BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)
    private lateinit var mainPage: MainPage

    @BeforeEach
    @Step("Initialize or run reusable methods for each test")
    fun setUp() {
        mainPage = MainPage()
    }


    @Feature("Login test")
    @DisplayName("Login test")
    @Severity(CRITICAL)
    @Test
    fun `Login test`() {
        mainPage
            .openMainPage()
            .clickLoginButton()
            .fillInEmailField(props.login())
            .fillInPassField(props.password())
            .clickLoginSubmitButton()
    }
}