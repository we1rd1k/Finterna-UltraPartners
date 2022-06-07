package com.ultrapartners.ui.tests.affiliate.smoke.steps

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.back
import com.codeborne.selenide.WebDriverRunner
import com.ultrapartners.ui.tests.data.SETTINGS
import com.ultrapartners.ui.tests.pages.BasePage
import com.ultrapartners.ui.tests.pages.RegistrationPage
import com.ultrapartners.ui.tests.pages.affiliate.AffiliateMainPage
import com.ultrapartners.ui.tests.pages.affiliate.ReportsPage
import com.ultrapartners.ui.tests.pages.affiliate.SettingsPage
import com.ultrapartners.ui.tests.utils.waitForJStoLoad
import io.qameta.allure.Step
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.awaitility.Awaitility
import java.time.Duration
import kotlin.test.assertContains

fun `Go to Page`(pageName: String) {
    AffiliateMainPage().goToPage(pageName)
}

fun `Affiliate Login`(login: String, pass: String) {
    BasePage().openMainPage()
        .clickLoginButton()
        .fillInEmailField(login)
        .fillInPassField(pass)
        .clickLoginSubmitButton(AffiliateMainPage())
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
        runBlocking {
            waitForJStoLoad
            delay(3000)
            AffiliateMainPage().affiliateSideMenu(s).click()
            val url = WebDriverRunner.getWebDriver().currentUrl
            Awaitility.await().atMost(Duration.ofMillis(5000)).untilAsserted { (assertContains(url, linksMap.getValue(s), ignoreCase = true)) }
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
        .fillInecoPayzField(ecoPayz)
        .clickApplyButton()
        .checkNetellerFieldSaved(neteller)
        .checkSkrillFieldSaved(skrill)
        .checkecoPayzFieldSaved(ecoPayz)
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

fun `Check registration fields - negative`(email: String, password: String) {
    RegistrationPage()
        .openMainPage()
        .clickJoinUsButton()
        .fillInEmailField(email, Condition.visible)
        .fillInPasswordField(password, Condition.visible)
}

fun `Check elements availability - dashBoard`() {
    AffiliateMainPage()
        .checkTimePeriodBarIsAvailable()
        .checkRenderingFilterIsAvailable()
        .checkPerformanceTableControlsAreAvailable()
}

fun `Check elements availability - Reports Page`() {
    ReportsPage()
        .weAreOnReportsPage()
        .checkReportsBarAvailable()
        .checkReportsMenuItemsAvailability()
}

fun `Check elements availability - Settings Page`() {
    SettingsPage()
        .weAreOnSettingsPage()
        .checkSettingsPageElementsAvailability()
}

