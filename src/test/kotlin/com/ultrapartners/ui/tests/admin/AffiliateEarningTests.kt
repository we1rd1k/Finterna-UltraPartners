package com.ultrapartners.ui.tests.admin

import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.admin.steps.`Admin Login`
import com.ultrapartners.ui.tests.admin.steps.`Fill in create affiliate's earning form`
import com.ultrapartners.ui.tests.admin.steps.`Go to affiliate earnings page`
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.Test

class AffiliateEarningTests: BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)

    @Test
    fun createNewAffiliateEarningTest() {
        `Admin Login`(props.adminLogin(), props.password())
        `Go to affiliate earnings page`()
        `Fill in create affiliate's earning form`()
    }
}