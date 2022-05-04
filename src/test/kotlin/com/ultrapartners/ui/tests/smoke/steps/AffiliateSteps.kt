package com.ultrapartners.ui.tests.smoke.steps

import com.codeborne.selenide.Selenide.back
import com.codeborne.selenide.WebDriverRunner
import com.ultrapartners.ui.tests.data.SETTINGS
import com.ultrapartners.ui.tests.pages.AffiliateMainPage
import com.ultrapartners.ui.tests.pages.BasePage
import com.ultrapartners.ui.tests.pages.RegistrationPage
import com.ultrapartners.ui.tests.pages.SettingsPage
import io.qameta.allure.Step
import kotlin.test.assertContains


fun `Affiliate Login`(login: String, pass: String) {
    BasePage().openMainPage()
        .clickLoginButton()
        .fillInEmailField(login)
        .fillInPassField(pass)
        .clickLoginSubmitButton()
        .weAreOnAffiliateMain()
}

fun `Affiliate Logout`() {
    AffiliateMainPage()
        .openAffiliateProfileDropDownList()
        .logout()
}

fun `Check availability of links`() {
    val linksNames = listOf("Reports", "Media", "Payments", "News", "Settings", "Support")
    val linksMap = mapOf(
        "Reports" to "/reports",
        "Media" to "/affiliate/marketing",
        "Payments" to "/site/payments",
        "News" to "/news/list",
        "Settings" to "/affiliate/my_account",
        "Support" to "/affiliate/support"
    )
    linksNames.forEach { s ->
        run {
            AffiliateMainPage().sideMenu(s).click()
            assertContains(WebDriverRunner.getWebDriver().currentUrl, linksMap.getValue(s), ignoreCase = true)
            if (s == "Media") back()
        }
    }
}

@Step("Change password from {currentPass} to {newPass}")
fun `Change password`(currentPass: String, newPass: String) {
    AffiliateMainPage().goToPage(SETTINGS)
    SettingsPage()
        .weAreOnSettingsPage()
        .goToChangePasswordMenu()
        .fillInCurrentPassField(currentPass)
        .fillInNewPassField(newPass)
        .fillInConfirmPassField(newPass)
        .submitPasswordChange()
        .closePassModal()

}

fun `Add payment info`(neteller: String, skrill: String, ecoPayz: String) {
    AffiliateMainPage().goToPage(SETTINGS)
    SettingsPage()
        .goToBillingDetails()
        .fillInNetellerField(neteller)
        .fillInSkrillField(skrill)
        .fillIecoPayzField(ecoPayz)
}

fun `Check registration fields`(firstName: String, lastName: String, email: String, password: String) {
    RegistrationPage()
        .openMainPage()
        .clickJoinUsButton()
        .fillInFirstNameField(firstName)
        .fillInLastNameField(lastName)
        .fillInEmailField(email)
        .fillInPasswordField(password)
}