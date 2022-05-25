package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$x`
import org.openqa.selenium.By


class AffiliateEarningsPage: BasePage() {

    private val affiliateEarningHeader = `$x`("//div[@id='page-name']//span[contains(text(), 'Affil Earnings')]")
    private val addNewEarning = `$x`("//div[@id='page-content']//a[contains(text(), 'Add new earning')]")
    private val createAffiliateEarningHeader = `$x`("//div[@id='page-name']//span[contains(text(), 'Create AffilEarning')]")
    private val affiliateIdField = `$`(By.name("AffilEarning[id_affil]"))
    private val periodField = `$`(By.name("AffilEarning[id_period]"))
    private val approvedCheckBox = `$`(By.id("AffilEarning_approved"))
    private val amountField = `$`(By.id("AffilEarning_amount"))
    private val commentField = `$`(By.id("AffilEarning_comment"))
    private val createButton = `$x`("//input[@type='submit']")



    fun weAreOnAffiliateEarningsPage(): AffiliateEarningsPage {
        affiliateEarningHeader.shouldBe(visible)
        return this
    }

    fun weAreOnCreateNewAffiliateEarningsPage(): AffiliateEarningsPage {
        createAffiliateEarningHeader.shouldBe(visible)
        return this
    }

    fun  goToCreateNewEarning(): AffiliateEarningsPage {
        addNewEarning.click()
        createAffiliateEarningHeader.shouldBe(visible)
        return this
    }

    fun fillInAffiliateIdField(value: String): AffiliateEarningsPage {
        affiliateIdField.value = value
        return this
    }

    fun fillInPeriodField(value: String): AffiliateEarningsPage {
        periodField.selectOption(value)
        return this
    }

    fun setApprovedCheckBox(isSet: Boolean): AffiliateEarningsPage {
        approvedCheckBox.isSelected = isSet
        return this
    }

    fun fillInAmountField(value: String): AffiliateEarningsPage {
        amountField.value = value
        return this
    }

    fun fillInCommentField(value: String): AffiliateEarningsPage {
        commentField.value = value
        return this
    }

    fun submitForm() {
        createButton.click()
        //TODO: check creation of affiliate after period dropdown fix
    }

}