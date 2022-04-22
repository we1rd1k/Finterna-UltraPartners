package ui.tests

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.logevents.SelenideLogger
import io.qameta.allure.Step
import io.qameta.allure.selenide.AllureSelenide
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.openqa.selenium.remote.DesiredCapabilities

@TestInstance(Lifecycle.PER_CLASS)
open class BaseTest {

    @BeforeAll
    @Step("Configure browser before all tests")
    fun beforeAllTests() {
        SelenideLogger.addListener(
            "AllureSelenide",
            AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        )
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("browserName", "chrome")
        capabilities.setCapability("browserVersion", "89.0")
        capabilities.setCapability("chrome.switches", listOf("--ignore-certificate-errors"))
        val value: MutableMap<String, Any> = HashMap()
        value["enableVNC"] = true
        value["enableVideo"] = true
        capabilities.setCapability("selenoid:options", value)
        Configuration.browserCapabilities = capabilities
        Configuration.browser = "chrome"
        Configuration.timeout = 10000
        Configuration.browserSize = "1366x768"
//        Configuration.headless = true
    }

    @AfterEach
    fun afterTest() {
        Selenide.closeWebDriver()
    }
}