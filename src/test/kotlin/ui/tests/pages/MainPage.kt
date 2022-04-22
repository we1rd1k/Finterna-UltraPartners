package ui.tests.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$x`
import com.codeborne.selenide.Selenide.open
import com.codeborne.selenide.WebDriverRunner
import io.qameta.allure.Step
import mu.KotlinLogging
import org.aeonbits.owner.ConfigFactory
import org.slf4j.Logger
import ui.tests.Props
import kotlin.test.assertEquals

class MainPage {

    private val log: Logger = KotlinLogging.logger { }
    private val props = ConfigFactory.create(Props::class.java)

    private val loginButton = `$x`("//div[@class = 'container-fluid']//button[@type = 'submit' and .='LOGIN']")


    @Step("Open app main web page")
    fun openMainPage(): MainPage {
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


}