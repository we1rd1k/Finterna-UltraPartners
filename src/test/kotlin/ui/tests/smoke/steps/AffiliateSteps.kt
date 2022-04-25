package ui.tests.smoke.steps

import ui.tests.pages.AffiliateMainPage
import ui.tests.pages.BasePage


fun `Affiliate Login`(login: String, pass: String){
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