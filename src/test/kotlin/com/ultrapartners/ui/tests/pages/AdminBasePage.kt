package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$x`
import io.qameta.allure.Step
import mu.KotlinLogging
import org.slf4j.Logger

class AdminBasePage: BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private val startPageName = `$x`("//div[@id='page-name']//div//span[text()='Affiliates']")
    private val firstNameBlock = `$x`("//ul[@id='user-block']/li[@name='first-name']")
    private val logOutButton = `$x`("//ul[@id='user-block']/li[@name='Logout']")

    private fun navBarMenu(name: String) = `$x`("//ul[contains(@class,'navbar')]//li[@class='dropdown']/a[contains(text(), '$name')]")
    private fun dropDownMenuElement(name: String) = `$x`("//ul[contains(@class,'dropdown-menu')]//li/a[contains(text(), '$name')]")

    @Step("Move mouse over Navigation bar Menu: {name}")
    private fun navigateToMenu(name: String) = navBarMenu(name).hover()

    @Step("Go to page: {pageName}")
    fun <T> goToPage(menuName: String, pageName: String, pageToGo: T): T {
        log.info("Go to page: $pageName")
        navigateToMenu(menuName)
        andClickDropDownMenuElement(pageName)
        return pageToGo
    }

    @Step("Click on {name} element from dropdown list")
    private fun andClickDropDownMenuElement(name: String) {
        log.info("Click on $name element from dropdown list")
        dropDownMenuElement(name).click()
    }

    @Step("Check we are on admin start page")
    fun weAreOnAdminBasePage(): AdminBasePage {
        log.info("Check we are on admin start page")
        startPageName.shouldBe(visible)
        firstNameBlock.shouldBe(visible)
        logOutButton.shouldBe(visible)
        return this
    }



}