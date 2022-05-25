package com.ultrapartners.ui.tests.admin

import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.admin.steps.`Admin Login`
import com.ultrapartners.ui.tests.admin.steps.`Fill in create affiliate's earning form`
import com.ultrapartners.ui.tests.admin.steps.`Go to affiliate earnings page`
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Epic("Affiliate Earnings")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Affiliate Earnings Tests")
class AffiliateEarningTests: BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)


    @Feature("[ADM][Affiliates][AffiliateEarnings] Create new affiliate earnings")
    @DisplayName("[ADM][Affiliates][AffiliateEarnings] Create new affiliate earnings test")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun `create New Affiliate Earning Test`() {
        `Admin Login`(props.adminLogin(), props.password())
        `Go to affiliate earnings page`()
        `Fill in create affiliate's earning form`()
    }
}