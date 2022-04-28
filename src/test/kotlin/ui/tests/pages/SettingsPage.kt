package ui.tests.pages

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
import io.qameta.allure.Step
import org.openqa.selenium.By

class SettingsPage : BasePage() {

    private val personalDetails = `$x`("//ul[@class='navbar-int-settings']//li[text()='Personal Details']")
    private val billingDetails = `$x`("//ul[@class='navbar-int-settings']//li[text()='Billing Details']")
    private val changePasswordButton = `$x`("//div[contains(@class, 'sidebar-right')]//section//p[@id='cng-pswd']")
    private val changePasswordMenu =
        `$x`("//div[contains(@class, 'ui-dialog-titlebar')]//span[text()='Change your password']")
    private val currentPasswordField = `$`(By.name("ChangePasswordForm[pass]"))
    private val newPasswordField = `$`(By.name("ChangePasswordForm[new_pass]"))
    private val confirmPasswordField = `$`(By.name("ChangePasswordForm[new_pass_confirm]"))

    private val netellerField = `$`(By.name("MyAccountForm[neteller_email]"))
    private val skrillField = `$`(By.name("MyAccountForm[skrill_email]"))
    private val ecoPayzField = `$`(By.name("MyAccountForm[payment_method][EcoPayz][Ecopayz ID]"))

    private val passwordSubmitButton = `$x`("//input[@value='CHANGE']")
    private val passwordChangeMessage = `$x`("//div[@id='change-password']/p[text()='Congratulations! Your password was changed!']")
    private val passwordModalCloseButton = `$x`("//div[@id='change-password']//preceding-sibling::div//span[.='close']")

    @Step("Check we are on Settings page")
    fun weAreOnSettingsPage(): SettingsPage {
        sectionsLink("Settings").shouldBe(visible)
        personalDetails.shouldBe(visible)
        return this
    }

    @Step("Go to change password menu")
    fun goToChangePasswordMenu(): SettingsPage {
        changePasswordButton.click()
        changePasswordMenu.shouldBe(visible)
        return this
    }

    @Step("Fill in current password field with: {currentPass}")
    fun fillInCurrentPassField(currentPass: String): SettingsPage {
        currentPasswordField.sendKeys(currentPass)
        return this
    }

    @Step("Fill in new password field with: {newPass}")
    fun fillInNewPassField(newPass: String): SettingsPage {
        newPasswordField.sendKeys(newPass)
        return this
    }

    @Step("Fill in confirm password field with: {confirmPass}")
    fun fillInConfirmPassField(confirmPass: String): SettingsPage {
        confirmPasswordField.sendKeys(confirmPass)
        return this
    }

    @Step("Submit password change")
    fun submitPasswordChange(): SettingsPage {
        passwordSubmitButton.click()
        passwordChangeMessage.shouldBe(visible)
        return this
    }

    @Step("Close Modal window")
    fun closePassModal(): SettingsPage {
        passwordModalCloseButton.click()
        return this
    }

    @Step("Go to billing details form")
    fun goToBillingDetails() {
        billingDetails.click()
        billingDetails.shouldHave(attributeMatching("class", ".*active.*"))
    }


}