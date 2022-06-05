package com.ultrapartners.ui.tests.pages.admin

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
import com.ultrapartners.ui.tests.pages.BasePage
import io.qameta.allure.Step
import mu.KotlinLogging
import org.openqa.selenium.By
import org.slf4j.Logger


class AffiliateEarningsPage: BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private val affiliateEarningHeader = `$x`("//div[@id='page-name']//span[contains(text(), 'Affil Earnings')]")
    private val addNewEarning = `$x`("//div[@id='page-content']//a[contains(text(), 'Add new earning')]")
    private val createAffiliateEarningHeader = `$x`("//div[@id='page-name']//span[contains(text(), 'Create AffilEarning')]")
    private val affiliateIdField = `$`(By.name("AffilEarning[id_affil]"))
    private val periodField = `$`(By.name("AffilEarning[id_period]"))
    private val approvedCheckBox = `$`(By.id("AffilEarning_approved"))
    private val amountField = `$`(By.id("AffilEarning_amount"))
    private val commentField = `$`(By.id("AffilEarning_comment"))
    private val createButton = `$x`("//input[@type='submit']")


    @Step("Check we are on Affiliate Earnings page")
    fun weAreOnAffiliateEarningsPage(): AffiliateEarningsPage {
        log.info("Check we are on Affiliate Earnings page")
        affiliateEarningHeader.shouldBe(visible)
        return this
    }

    @Step("Check we are on Create New Affiliate Earnings page")
    fun weAreOnCreateNewAffiliateEarningsPage(): AffiliateEarningsPage {
        log.info("Check we are on Create New Affiliate Earnings page")
        createAffiliateEarningHeader.shouldBe(visible)
        return this
    }

    @Step("Go to create New earning form")
    fun  goToCreateNewEarning(): AffiliateEarningsPage {
        log.info("Go to create New earning form")
        addNewEarning.click()
        createAffiliateEarningHeader.shouldBe(visible)
        return this
    }

    @Step("Fill in affiliateId field with value: {value}")
    fun fillInAffiliateIdField(value: String): AffiliateEarningsPage {
        log.info("Fill in affiliateId field with value: $value")
        affiliateIdField.setValue(value)
        return this
    }

    @Step("Fill in Period field with value: {value}")
    fun fillInPeriodField(value: String): AffiliateEarningsPage {
        log.info("Fill in Period field with value: $value")
        periodField.selectOption(value)
        return this
    }

    @Step("Set Approved check box to: {isSet}")
    fun setApprovedCheckBox(isSet: Boolean): AffiliateEarningsPage {
        log.info("Set Approved check box to: $isSet")
        approvedCheckBox.isSelected = isSet
        return this
    }

    @Step("Fill in Amount field with value: {value}")
    fun fillInAmountField(value: String): AffiliateEarningsPage {
        log.info("Fill in Amount field with value: $value")
        amountField.setValue(value)
        return this
    }

    @Step("Fill in Comment field with value: {value}")
    fun fillInCommentField(value: String): AffiliateEarningsPage {
        log.info("Fill in Comment field with value: $value")
        commentField.setValue(value)
        return this
    }

    @Step("Click submit button and create earning")
    fun submitForm() {
        log.info("Click submit button and create earning")
        createButton.click()
        //TODO: check creation of affiliate after period dropdown fix
    }

}