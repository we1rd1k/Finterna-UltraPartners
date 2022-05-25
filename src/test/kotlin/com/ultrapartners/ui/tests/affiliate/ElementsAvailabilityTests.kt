package com.ultrapartners.ui.tests.affiliate

import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.affiliate.smoke.steps.*
import com.ultrapartners.ui.tests.data.REPORTS
import com.ultrapartners.ui.tests.data.SETTINGS
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.Test

class ElementsAvailabilityTests: BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)

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