package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
import com.ultrapartners.ui.tests.utils.clickable
import io.qameta.allure.Step
import mu.KotlinLogging
import org.openqa.selenium.By
import org.slf4j.Logger

class SettingsPage : BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private val personalDetails = `$x`("//ul[@class='navbar-int-settings']//li[text()='Personal Details']")
    private val billingDetails = `$x`("//ul[@class='navbar-int-settings']//li[text()='Billing Details']")
    private val contactInformation = `$x`("//ul[@class='navbar-int-settings']//li[text()='Contact information']")
    private val preferences = `$x`("//ul[@class='navbar-int-settings']//li[text()='Preferences']")
    private val changePasswordButton = `$x`("//div[contains(@class, 'sidebar-right')]//section//p[@id='cng-pswd']")
    private val manageApiToken = `$x`("//div[contains(@class, 'sidebar-right')]//section//p[@id='cng-pswd']")
    private val changePasswordMenu =
        `$x`("//div[contains(@class, 'ui-dialog-titlebar')]//span[text()='Change your password']")
    private val currentPasswordField = `$`(By.name("ChangePasswordForm[pass]"))
    private val newPasswordField = `$`(By.name("ChangePasswordForm[new_pass]"))
    private val confirmPasswordField = `$`(By.name("ChangePasswordForm[new_pass_confirm]"))

    private val netellerField = `$`(By.name("MyAccountForm[neteller_email]"))
    private val skrillField = `$`(By.name("MyAccountForm[skrill_email]"))
    private val ecoPayzField = `$`(By.name("MyAccountForm[payment_method][EcoPayz][Ecopayz ID]"))
    private val applyButton = `$x`("//input[@value='APPLY']")

    private val passwordSubmitButton = `$x`("//input[@value='CHANGE']")
    private val passwordChangeMessage =
        `$x`("//div[@id='change-password']/p[text()='Congratulations! Your password was changed!']")
    private val passwordModalCloseButton = `$x`("//div[@id='change-password']//preceding-sibling::div//span[.='close']")

    @Step("Check we are on Settings page")
    fun weAreOnSettingsPage(): SettingsPage {
        log.info("Check we are on Settings page")
        sideMenu("Settings").shouldBe(visible)
        personalDetails.shouldBe(visible)
        return this
    }

    @Step("Go to change password menu")
    fun goToChangePasswordMenu(): SettingsPage {
        log.info("Go to change password menu")
        changePasswordButton.click()
        changePasswordMenu.shouldBe(visible)
        return this
    }

    @Step("Fill in current password field with: {currentPass}")
    fun fillInCurrentPassField(currentPass: String): SettingsPage {
        log.info("Fill in current password field with: $currentPass")
        currentPasswordField.sendKeys(currentPass)
        return this
    }

    @Step("Fill in new password field with: {newPass}")
    fun fillInNewPassField(newPass: String): SettingsPage {
        log.info("Fill in new password field with: $newPass")
        newPasswordField.sendKeys(newPass)
        return this
    }

    @Step("Fill in confirm password field with: {confirmPass}")
    fun fillInConfirmPassField(confirmPass: String): SettingsPage {
        log.info("Fill in confirm password field with: $confirmPass")
        confirmPasswordField.sendKeys(confirmPass)
        return this
    }

    @Step("Submit password change")
    fun submitPasswordChange(): SettingsPage {
        log.info("Submit password change")
        passwordSubmitButton.click()
        passwordChangeMessage.shouldBe(visible)
        return this
    }

    @Step("Close Modal window")
    fun closePassModal(): SettingsPage {
        log.info("Close Modal window")
        passwordModalCloseButton.click()
        return this
    }

    @Step("Go to billing details form")
    fun goToBillingDetails(): SettingsPage {
        log.info("Go to billing details form")
        billingDetails.click()
        billingDetails.shouldHave(attributeMatching("class", ".*active.*"))
        return this
    }

    @Step("fill In Neteller Field with: {input}")
    fun fillInNetellerField(input: String): SettingsPage {
        log.info("fill In Neteller Field with: $input")
        netellerField.clear()
        netellerField.sendKeys(input)
        return this
    }

    @Step("fill In Skrill Field with: {input}")
    fun fillInSkrillField(input: String): SettingsPage {
        log.info("fill In Skrill Field with: $input")
        skrillField.clear()
        skrillField.sendKeys(input)
        return this
    }

    @Step("fill In ecoPayz Field with: {input}")
    fun fillInecoPayzField(input: String): SettingsPage {
        log.info("fill In ecoPayz Field with: $input")
        ecoPayzField.clear()
        ecoPayzField.sendKeys(input)
        return this
    }

    @Step("click Apply Button")
    fun clickApplyButton(): SettingsPage {
        log.info("click Apply Button")
        applyButton.click()
        return this
    }

    @Step("check Neteller Field Saved and value is: {value}")
    fun checkNetellerFieldSaved(value: String): SettingsPage {
        log.info("check Neteller Field Saved and value is: $value")
        netellerField.shouldHave(value(value))
        return this
    }

    @Step("check Skrill Field Saved and value is: {value}")
    fun checkSkrillFieldSaved(value: String): SettingsPage {
        log.info("check Skrill Field Saved and value is: $value")
        skrillField.shouldHave(value(value))
        return this
    }

    @Step("check ecoPayz Field Saved and value is: {value}")
    fun checkecoPayzFieldSaved(value: String): SettingsPage {
        log.info("check ecoPayz Field Saved and value is: $value")
        ecoPayzField.shouldHave(value(value))
        return this
    }

    fun checkSettingsPageElementsAvailability(): SettingsPage {
        personalDetails.shouldBe(clickable)
        billingDetails.shouldBe(clickable)
        contactInformation.shouldBe(clickable)
        preferences.shouldBe(clickable)
        changePasswordButton.shouldBe(clickable)
        manageApiToken.shouldBe(clickable)
        return this
    }
}