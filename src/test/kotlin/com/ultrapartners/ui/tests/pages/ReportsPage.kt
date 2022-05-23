package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Selenide.`$$x`
import com.codeborne.selenide.Selenide.`$x`
import com.ultrapartners.ui.tests.utils.clickable
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


    fun weAreOnReportsPage(): ReportsPage {
        reportBarItemsList.shouldHave(CollectionCondition.exactTexts("Tracker", "Sources", "Subaff", "Countries", "Media", "Platform", "Daily"))
        return this
    }

    fun checkReportsBarAvailable(): ReportsPage {
        reportBarItemsList.forEach { it.shouldBe(clickable) }
        return this
    }

    fun checkReportsMenuItemsAvailability(): ReportsPage {
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

