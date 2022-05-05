package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Condition.hidden
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
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
    private val errorMessage = `$x`("//..//div[@class='errorMsg-join']")


    @Step("Fill in firstName field: {firstName}")
    fun fillInFirstNameField(firstName: String): RegistrationPage {
        log.info("Fill in firstName field: $firstName")
        firstNameField.sendKeys(firstName)
        firstNameField.sendKeys(Keys.ENTER)
        `$x`("//input[@id='pd-name']//..//div[@class='errorMsg-join']").shouldBe(hidden)
        return this
    }

    @Step("Fill in lastName field: {lastName}")
    fun fillInLastNameField(lastName: String): RegistrationPage {
        log.info("Fill in lastName field: $lastName")
        lastNameField.sendKeys(lastName)
        lastNameField.sendKeys(Keys.ENTER)
        `$x`("//input[@id='join-s-name']//..//div[@class='errorMsg-join']").shouldBe(hidden)
        return this
    }

    @Step("Fill in email field: {email}")
    fun fillInEmailField(email: String, errorMessage: Condition? = hidden): RegistrationPage {
        log.info("Fill in email field: $email")
        emailField.sendKeys(email)
        emailField.sendKeys(Keys.ENTER)
        `$x`("//input[@id='join-email']//..//div[@class='errorMsg-join']").shouldBe(errorMessage)
        return this
    }

    @Step("Fill in password field: {password}")
    fun fillInPasswordField(password: String, errorMessage: Condition? = hidden): RegistrationPage {
        log.info("Fill in lastName field: $password")
        passwordField.sendKeys(password)
        passwordField.sendKeys(Keys.ENTER)
        `$x`("//input[@id='cd-pass']//..//div[@class='errorMsg-join']").shouldBe(errorMessage)
        return this
    }

}