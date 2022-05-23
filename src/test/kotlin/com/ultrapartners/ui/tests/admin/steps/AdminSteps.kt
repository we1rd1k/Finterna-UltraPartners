package com.ultrapartners.ui.tests.admin.steps

import com.ultrapartners.ui.tests.pages.AdminBasePage
import com.ultrapartners.ui.tests.pages.AffiliateEarningsPage
import com.ultrapartners.ui.tests.pages.BasePage

fun `Admin Login`(login: String, pass: String) {
    BasePage().openMainPage()
        .clickLoginButton()
        .fillInEmailField(login)
        .fillInPassField(pass)
        .clickLoginSubmitButton(pageToGo = AdminBasePage())
        .weAreOnAdminBasePage()
}

fun `Go to affiliate earnings page`() {
    AdminBasePage()
        .goToPage(menuName = "Affiliates", pageName = "Affiliate Earnings", pageToGo = AffiliateEarningsPage())
        .weAreOnAffiliateEarningsPage()
}

//TODO: uncomment after dropdown fix
fun `Fill in create affiliate's earning form`(
    affiliateId: String = "1234",
    period: String = "Test",
    approvedCheckBox: Boolean = true,
    amount: String = "123321",
    comment: String = "Tester"
) {
    AffiliateEarningsPage()
        .goToCreateNewEarning()
        .weAreOnCreateNewAffiliateEarningsPage()
        .fillInAffiliateIdField(affiliateId)
//        .fillInPeriodField(period)
        .setApprovedCheckBox(approvedCheckBox)
        .fillInAmountField(amount)
        .fillInCommentField(comment)
        .submitForm()
}

fun `Admin Logout`() {
}