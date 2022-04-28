package ui.tests.smoke.steps

import com.codeborne.selenide.Selenide.back
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Step
import ui.tests.pages.AffiliateMainPage
import ui.tests.pages.BasePage
import ui.tests.pages.SettingsPage
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
            AffiliateMainPage().sectionsLink(s).click()
            assertContains(WebDriverRunner.getWebDriver().currentUrl, linksMap.getValue(s), ignoreCase = true)
            if (s == "Media") back()
        }
    }
}

@Step("Change password from {currentPass} to {newPass}")
fun `Change password`(currentPass: String, newPass: String) {
    AffiliateMainPage().goToPage("Settings")
    SettingsPage()
        .weAreOnSettingsPage()
        .goToChangePasswordMenu()
        .fillInCurrentPassField(currentPass)
        .fillInNewPassField(newPass)
        .fillInConfirmPassField(newPass)
        .submitPasswordChange()
        .closePassModal()

}