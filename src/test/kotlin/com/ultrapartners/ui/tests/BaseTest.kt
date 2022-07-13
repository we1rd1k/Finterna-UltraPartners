package com.ultrapartners.ui.tests

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.Step
import io.qameta.allure.selenide.AllureSelenide
import mu.KotlinLogging
import org.aeonbits.owner.ConfigFactory
import org.apache.commons.lang3.StringUtils
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.openqa.selenium.remote.DesiredCapabilities
import org.slf4j.Logger

@TestInstance(Lifecycle.PER_CLASS)
open class BaseTest {

    private val props = ConfigFactory.create(Props::class.java)
    private val log: Logger = KotlinLogging.logger { }

    @BeforeAll
    @Step("Configure browser before all tests")
    fun beforeAllTests() {
        SelenideLogger.addListener(
            "AllureSelenide",
            AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        )
        if (!StringUtils.isBlank(props.selenoidUrl())) {
            log.info("Selenoid Url" + props.selenoidUrl())
            Configuration.remote = props.selenoidUrl()
        }
        val capabilities = DesiredCapabilities()
//        capabilities.setCapability("browserName", "chrome")
//        capabilities.setCapability("browserVersion", "89.0")
        capabilities.setCapability("chrome.switches", listOf("--ignore-certificate-errors"))
        val value: MutableMap<String, Any> = HashMap()
        value["enableVNC"] = true
        value["enableVideo"] = true
        capabilities.setCapability("selenoid:options", value)
        Configuration.browserCapabilities = capabilities
        Configuration.browser = "chrome"
        Configuration.timeout = 10000
        Configuration.browserSize = "1366x768"
        Selenide.clipboard().text = ""
//        Configuration.headless = true
    }

    @AfterEach
    fun afterTest() {
        Selenide.closeWebDriver()
    }

    @AfterAll
    fun tearDown() {
        Selenide.clearBrowserCookies()
        Selenide.clearBrowserLocalStorage()
    }
}