package com.ultrapartners.ui.tests.affiliate

import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.affiliate.smoke.steps.*
import com.ultrapartners.ui.tests.data.REPORTS
import com.ultrapartners.ui.tests.data.SETTINGS
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import ui.tests.data.SMOKE

@Tag(SMOKE)
@Epic("Elements Availability")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Elements Availability Tests")
class ElementsAvailabilityTests: BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)

    @Feature("Elements Availability")
    @DisplayName("Availability of Affiliate Acc Elements Test")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun `Availability of Affiliate Acc Elements Test`() {
        `Affiliate Login`(props.affiliateLogin(), props.password())
        `Check elements availability - dashBoard`()
        `Go to Page`(REPORTS)
        `Check elements availability - Reports Page`()
        `Go to Page`(SETTINGS)
        `Check elements availability - Settings Page`()
    }
}