package com.ultrapartners.ui.tests.utils

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.executeJavaScript
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


var clickable: Condition = Condition.and("Element is clickable", Condition.visible, Condition.enabled)

var waitForJStoLoad = waitForJStoLoad()


fun waitForJStoLoad(): Boolean {
    val wait = WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(30))

    val jQueryLoad: ExpectedCondition<Boolean> = ExpectedCondition {
        try {
            return@ExpectedCondition executeJavaScript<Any>("return jQuery.active") as Long? == 0L
        } catch (e: Exception) {
            return@ExpectedCondition true
        }
    }
    val jsLoad: ExpectedCondition<Boolean> = ExpectedCondition {
        executeJavaScript<Any>("return document.readyState")
            .toString() == "complete"
    }
    return wait.until(jQueryLoad) && wait.until(jsLoad)
}