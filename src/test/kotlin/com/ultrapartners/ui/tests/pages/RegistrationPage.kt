package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.hidden
import com.codeborne.selenide.Selenide.`$`
import io.qameta.allure.Step
import mu.KotlinLogging
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.slf4j.Logger

class RegistrationPage : BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private val firstNameField = `$`(By.id("pd-name"))
    private val lastNameField = `$`(By.id("join-s-name"))
    private val emailField = `$`(By.id("join-email"))
    private val passwordField = `$`(By.id("cd-pass"))
    private val confirmPasswordField = `$`(By.id("cd-pass-con"))

    private val errorMessage = ".//..//div[@class='errorMsg-join']"

    @Step("Fill in firstName field: {firstName}")
    fun fillInFirstNameField(firstName: String): RegistrationPage {
        log.info("Fill in firstName field: $firstName")
        firstNameField.sendKeys(firstName)
        firstNameField.sendKeys(Keys.ENTER)
        firstNameField.`$x`(errorMessage).shouldBe(hidden)
        return this
    }

    @Step("Fill in lastName field: {lastName}")
    fun fillInLastNameField(lastName: String): RegistrationPage {
        log.info("Fill in lastName field: $lastName")
        lastNameField.sendKeys(lastName)
        lastNameField.sendKeys(Keys.ENTER)
        lastNameField.`$x`(errorMessage).shouldBe(hidden)
        return this
    }

    @Step("Fill in email field: {email}")
    fun fillInEmailField(email: String, errorMessageStatus: Condition? = hidden): RegistrationPage {
        log.info("Fill in email field: $email")
        emailField.sendKeys(email)
        emailField.sendKeys(Keys.ENTER)
        emailField.`$x`(errorMessage).shouldBe(errorMessageStatus)
        return this
    }

    @Step("Fill in password field: {password}")
    fun fillInPasswordField(password: String, errorMessageStatus: Condition? = hidden): RegistrationPage {
        log.info("Fill in lastName field: $password")
        passwordField.sendKeys(password)
        passwordField.sendKeys(Keys.ENTER)
        passwordField.`$x`(errorMessage).shouldBe(errorMessageStatus)
        return this
    }

}