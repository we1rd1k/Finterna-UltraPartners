package ui.tests.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$x`
import io.qameta.allure.Step
import mu.KotlinLogging
import org.slf4j.Logger


class AffiliateMainPage: BasePage() {

    private val log: Logger = KotlinLogging.logger { }


    private val logoutButton = `$x`("//div[contains(@class, 'dropdown-content')]//p//a[text()='Logout']")
    private val affiliateProfileButton = `$x`("//div[contains(@class, 'menu-top-switch')]//div")
    private val dropDownMenu = `$x`("//section[contains(@class, 'greeting')]//div//p[contains(text(), 'Affiliate')]")
    private val dashBoard = `$x`("//nav[contains(@class, 'sidebar-left')]//span[text()='Dashboard']")

    fun weAreOnAffiliateMain() {
        dashBoard.shouldBe(visible)
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

}