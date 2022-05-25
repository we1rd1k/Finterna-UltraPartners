package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.`$$x`
import com.codeborne.selenide.Selenide.`$x`
import com.ultrapartners.ui.tests.utils.clickable
import io.qameta.allure.Step
import mu.KotlinLogging
import org.slf4j.Logger

class ReportsPage : BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private val reportBarItemsList = `$$x`("//ul[contains(@class, 'navbar-int-report')]//li")

    private val runReportButton = `$x`("//input[@value='Run report']")
    private val exportReportButton = `$x`("//input[@value='Export']")

    //Reports menu items
    private fun reportDropdownElement(elementName: String) =
        `$x`("//form[@id='report-data-range']//div//h6[text()='$elementName']//following-sibling::*")


    @Step("Check we are on Reports page")
    fun weAreOnReportsPage(): ReportsPage {
        log.info("Check we are on Reports page")
        reportBarItemsList.shouldHave(CollectionCondition.exactTexts("Tracker", "Sources", "Subaff", "Countries", "Media", "Platform", "Daily"))
        return this
    }

    @Step("Check Reports Bar is available")
    fun checkReportsBarAvailable(): ReportsPage {
        log.info("Check Reports Bar is available")
        reportBarItemsList.forEach { it.shouldBe(clickable) }
        return this
    }

    @Step("Check Reports Menu Items are available")
    fun checkReportsMenuItemsAvailability(): ReportsPage {
        log.info("Check Reports Menu Items are available")
        listOf("Report period", "From Date", "To Date", "Tracker", "Source", "Brand")
            .forEach { i ->
            reportDropdownElement(i).shouldBe(
                clickable
            )
        }
        runReportButton.shouldBe(clickable)
        exportReportButton.shouldBe(clickable)
        return this
    }


}

