package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$x`
import io.qameta.allure.Step

class AdminBasePage: BasePage() {

    private val startPageName = `$x`("//div[@id='page-name']//div//span[text()='Affiliates']")
    private val firstNameBlock = `$x`("//ul[@id='user-block']/li[@name='first-name']")
    private val logOutButton = `$x`("//ul[@id='user-block']/li[@name='Logout']")

    private fun navBarMenu(name: String) = `$x`("//ul[contains(@class,'navbar')]//li[@class='dropdown']/a[contains(text(), '$name')]")
    fun dropDownMenuElement(name: String) = `$x`("//ul[contains(@class,'dropdown-menu')]//li/a[contains(text(), '$name')]")

    private fun navigateToMenu(name: String) = navBarMenu(name).hover()

    fun <T> goToPage(menuName: String, pageName: String, pageToGo: T): T {
        navigateToMenu(menuName)
        andClickDropDownMenuElement(pageName)
        return pageToGo
    }

    private fun andClickDropDownMenuElement(name: String) {
        dropDownMenuElement(name).click()
    }

    @Step("Check we are on admin start page")
    fun weAreOnAdminBasePage(): AdminBasePage {
        startPageName.shouldBe(visible)
        firstNameBlock.shouldBe(visible)
        logOutButton.shouldBe(visible)
        return this
    }



}