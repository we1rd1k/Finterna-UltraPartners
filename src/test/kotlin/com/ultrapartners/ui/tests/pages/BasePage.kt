package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.WebDriverRunner
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.data.cookies
import io.qameta.allure.Step
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.aeonbits.owner.ConfigFactory
import org.slf4j.Logger
import kotlin.test.assertEquals

open class BasePage {

    private val log: Logger = KotlinLogging.logger { }
    private val props = ConfigFactory.create(Props::class.java)
    internal val loginButton = `$x`("//div[@class = 'container-fluid']//button[@type = 'submit' and .='LOGIN']")
    internal val joinUsButton = `$x`("//div[@class = 'container-fluid']//button[@type = 'submit' and .='JOIN US']")
    internal val loginPageHeader = `$x`("//h1[text()='LOGIN ULTRA PARTNERS']")
    internal val registrationPageHeader = `$x`("//h1[text()='JOIN ULTRA PARTNERS']")


    @Step("Open app main web page")
    fun openMainPage(): BasePage {
        return if (cookies.isEmpty()) {
            log.info("Open web page ${props.ultraPartnersUrl()}")
            open(props.ultraPartnersUrl())
            loginButton.shouldBe(Condition.visible)
                .shouldBe(
                    Condition.enabled
                )
            this
        } else {
            log.info("Open web page ${props.ultraPartnersUrl()} with cookies")
            open(props.ultraPartnersUrl())
            WebDriverRunner.getWebDriver().manage().deleteAllCookies()
            WebDriverRunner.getWebDriver().manage()
                .addCookie(cookies.find { cookie -> cookie.name.equals("PHPSESSID") })
            runBlocking { delay(3000) }
            WebDriverRunner.getWebDriver().navigate().refresh()
            this
        }
    }

    @Step("Click login button")
    fun clickLoginButton(): LoginPage {
        log.info("Click login button")
        loginButton.click()
        loginPageHeader.shouldBe(Condition.visible)
        assertEquals("${props.ultraPartnersUrl()}/login", WebDriverRunner.getWebDriver().currentUrl)
        return LoginPage()
    }

    @Step("Click JoinUs button")
    fun clickJoinUsButton(): RegistrationPage {
        log.info("Click JoinUs button")
        joinUsButton.click()
        registrationPageHeader.shouldBe(Condition.visible)
        assertEquals("${props.ultraPartnersUrl()}/join", WebDriverRunner.getWebDriver().currentUrl)
        return RegistrationPage()
    }

    @Step("Choose section of affiliate side menu: {section}")
    fun affiliateSideMenu(section: String) = `$x`("//nav[contains(@class, 'sidebar-left')]//span[text()='$section']")


}