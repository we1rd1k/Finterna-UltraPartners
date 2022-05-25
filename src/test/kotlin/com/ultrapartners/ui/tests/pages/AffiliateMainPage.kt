package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$$x`
import com.codeborne.selenide.Selenide.`$x`
import com.ultrapartners.ui.tests.data.DASHBOARD
import com.ultrapartners.ui.tests.utils.clickable
import io.qameta.allure.Step
import mu.KotlinLogging
import org.slf4j.Logger


class AffiliateMainPage : BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private val timePeriodBarItemsList = `$$x`("//ul[contains(@class, 'center')]//li")

    private val logoutButton = `$x`("//div[contains(@class, 'dropdown-content')]//p//a[text()='Logout']")
    private val affiliateProfileButton = `$x`("//div[contains(@class, 'menu-top-switch')]//div")
    private val dropDownMenu = `$x`("//section[contains(@class, 'greeting')]//div//p[contains(text(), 'Affiliate')]")
    private val tableRenderingFilter = `$x`("//p[.='Your performence over the month']//following-sibling::button[contains(@class, 'table')]")
    private val chartRenderingFilter = `$x`("//p[.='Your performence over the month']//following-sibling::button[contains(@class, 'chart')]")

    private fun performanceTableControlElement(elementName: String) = `$x`("//div[@class='apexcharts-toolbar']/div[@title='$elementName']")

    @Step("Check we are on affiliate main page")
    fun weAreOnAffiliateMain() {
        affiliateSideMenu(DASHBOARD).shouldBe(visible)
    }

    @Step("Open account menu")
    fun openAffiliateProfileDropDownList(): AffiliateMainPage {
        log.info("Open account menu")
        affiliateProfileButton.click()
        dropDownMenu.shouldBe(visible)
        return this
    }

    @Step("Logout from affiliate")
    fun logout(): BasePage {
        log.info("Logout from affiliate")
        logoutButton.click()
        loginButton.shouldBe(visible)
        return BasePage()
    }

    @Step("Go to {pageName} page")
    fun goToPage(pageName: String) {
        log.info("Go to $pageName page")
        affiliateSideMenu(pageName).click()
    }

    @Step("Check Time period bar is available")
    fun checkTimePeriodBarIsAvailable(): AffiliateMainPage {
        log.info("Check Time period bar is available")
        timePeriodBarItemsList.forEach { it.shouldBe(clickable) }
        return this
    }

    @Step("Check Rendering Filter is available")
    fun checkRenderingFilterIsAvailable(): AffiliateMainPage {
        log.info("Check Rendering Filter is available")
        tableRenderingFilter.shouldBe(clickable)
        chartRenderingFilter.shouldBe(clickable)
        return this
    }

    @Step("Check Performance Table Controls are available")
    fun checkPerformanceTableControlsAreAvailable(): AffiliateMainPage {
        log.info("Check Performance Table Controls are available")
        performanceTableControlElement("Zoom In").shouldBe(clickable)
        performanceTableControlElement("Zoom Out").shouldBe(clickable)
        performanceTableControlElement("Selection Zoom").shouldBe(clickable)
        performanceTableControlElement("Panning").shouldBe(clickable)
        performanceTableControlElement("Reset Zoom").shouldBe(clickable)
        performanceTableControlElement("Menu").shouldBe(clickable)
        return this
    }


}