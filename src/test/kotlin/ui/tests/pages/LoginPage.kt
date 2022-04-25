package ui.tests.pages

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
import io.qameta.allure.Step
import mu.KotlinLogging
import org.openqa.selenium.By
import org.slf4j.Logger

class LoginPage {

    private val log: Logger = KotlinLogging.logger { }

    private val emailField = `$`(By.id("email-log"))
    private val password = `$`(By.id("pass"))
    private val submitButton = `$x`("//button[@type = 'submit' and .='LOG IN']")
    private val loginFailedMessage = `$x`("//div[contains(text(), 'Login failed: Invalid username or password')]")

    @Step("Fill in login field: {email}")
    fun fillInEmailField(email: String): LoginPage {
        log.info("Fill in login field: {0}")
        emailField.sendKeys(email)
        return this
    }
    @Step("Fill in password field: {pass}")
    fun fillInPassField(pass: String): LoginPage {
        log.info("Fill in password field: {0}")
        password.sendKeys(pass)
        return this
    }

    @Step("Click Log in button")
    fun clickLoginSubmitButton(): AffiliateMainPage {
        log.info("Click Log in button, and wait dashboard appears")
        submitButton.click()
        return AffiliateMainPage()
    }
}