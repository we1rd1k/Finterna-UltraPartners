package com.ultrapartners.ui.tests.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Step
import mu.KotlinLogging
import org.aeonbits.owner.ConfigFactory
import org.slf4j.Logger
import com.ultrapartners.ui.tests.Props
import kotlin.test.assertEquals

open class BasePage {

    private val log: Logger = KotlinLogging.logger { }
    private val props = ConfigFactory.create(Props::class.java)
    internal val loginButton = `$x`("//div[@class = 'container-fluid']//button[@type = 'submit' and .='LOGIN']")
    internal val joinUsButton = `$x`("//div[@class = 'container-fluid']//button[@type = 'submit' and .='JOIN US']")


    @Step("Open app main web page")
    fun openMainPage(): BasePage {
        log.info("Open web page ${props.ultraPartnersUrl()}")
        open(props.ultraPartnersUrl())
        loginButton.shouldBe(Condition.visible)
            .shouldBe(
                Condition.enabled
            )
        return this
    }

    @Step("Click login button")
    fun clickLoginButton(): LoginPage {
        log.info("Click login button")
        loginButton.click()
        `$x`("//h1[text()='LOGIN ULTRA PARTNERS']").shouldBe(Condition.visible)
        assertEquals("${props.ultraPartnersUrl()}/login", WebDriverRunner.getWebDriver().currentUrl)
        return LoginPage()
    }

    @Step("Click JoinUs button")
    fun clickJoinUsButton(): RegistrationPage {
        log.info("Click JoinUs button")
        joinUsButton.click()
        `$x`("//h1[text()='JOIN ULTRA PARTNERS']").shouldBe(Condition.visible)
        assertEquals("${props.ultraPartnersUrl()}/join", WebDriverRunner.getWebDriver().currentUrl)
        return RegistrationPage()
    }

    fun sideMenu(section: String) = `$x`("//nav[contains(@class, 'sidebar-left')]//span[text()='$section']")


}