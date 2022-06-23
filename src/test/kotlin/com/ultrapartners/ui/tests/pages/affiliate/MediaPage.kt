package com.ultrapartners.ui.tests.pages.affiliate

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.*
import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import com.ultrapartners.ui.tests.data.MEDIA
import com.ultrapartners.ui.tests.data.TRACKING_LINK_GENERATOR
import com.ultrapartners.ui.tests.data.dataId
import com.ultrapartners.ui.tests.pages.BasePage
import com.ultrapartners.ui.tests.utils.waitForJStoLoad
import io.qameta.allure.Step
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.junit.platform.commons.util.StringUtils
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WindowType
import org.slf4j.Logger
import kotlin.test.assertContains
import kotlin.test.assertEquals

class MediaPage : BasePage() {

    private val log: Logger = KotlinLogging.logger { }

    private fun mediaPageLink(name: String) = `$x`("//ul[@class='navbar-int-report']//li[contains(text(), '$name')]")
    private val affiliateDropDown = `$`(By.id("affiliate"))
    private val brandDropDown = `$`(By.id("brand"))
    private val languageDropDown = `$`(By.id("language"))
    private val targetPageDropDown = `$`(By.id("landing_page"))
    private val styleDropDown = `$`(By.id("style"))
    private val sourceField = `$`(By.id("campaign"))
    private val mediaCodeArea = `$`(By.name("link_code"))
    private val copyToClipBoardButtonTrackLinkGen =
        `$`(By.name("copy-to-clipboard"))//`$`(By.id("action__copy_to_clipboard"))
    private val copyToClipBoardButton = `$`(By.name("copy-to-clipboard"))
    private val brandFilterDropDown = `$`(By.id("MarketingTools_id_brand"))
    private val sizeFilterDropDown = `$`(By.id("MarketingTools_id_size"))
    private val languageFilterDropDown = `$`(By.id("MarketingTools_id_language"))
    private val providerFilterDropDown = `$`(By.id("MarketingTools_provider_id"))
    private val filterSearchButton = `$x`("//input[@value='Search']")
    private val trackingFooters = `$$x`("//h5[@class='tracking-footer']")
    private val trackingCode = `$x`("//div[contains(@id, 'preview')]//span[text()='Tracking code']")
    private val mailersLanguageDropDown = `$`(By.id("lang"))

    private fun trackingCodeSection(sectionId: String) =
        `$x`("//section[contains(@class, 'tracking-media') and contains(@id,'$sectionId')]")


    @Step("Check we are on Media page")
    fun weAreOnMediaPage(): MediaPage {
        log.info("Check we are on Media page")
        runBlocking { delay(3000) }
        affiliateSideMenu(MEDIA).shouldBe(Condition.visible)
        mediaPageLink(TRACKING_LINK_GENERATOR).shouldBe(Condition.visible)
        assertContains(WebDriverRunner.getWebDriver().currentUrl, "/affiliate/marketing")
        return this
    }

    @Step("Switch to module: {moduleName}")
    fun switchToModule(moduleName: String): MediaPage {
        log.info("Switch to module: $moduleName")
        runBlocking { delay(3000) }
        mediaPageLink(moduleName).scrollIntoView(false).click()
        `$x`("//li[contains(text(), '$moduleName')]")
            .shouldBe(Condition.attributeMatching("class", ".*active"))
        return this
    }


    private fun chooseElementFromDropDown(element: SelenideElement, value: String): MediaPage {
        element.selectOption(value)
        return this
    }

    @Step("Choose Elements From DropDown for TrackLink Generator: {affiliate}, {brand}, {language}, {targetPage}, {source}")
    fun setOptionForTrackLinkGenerator(
        affiliate: String,
        brand: String,
        language: String,
        targetPage: String,
        source: String
    ): MediaPage {
        log.info("Choose Elements From DropDown for TrackLink Generator: $affiliate, $brand, $language, $targetPage, $source")
        chooseElementFromDropDown(affiliateDropDown, affiliate)
        chooseElementFromDropDown(brandDropDown, brand)
        chooseElementFromDropDown(languageDropDown, language)
        chooseElementFromDropDown(targetPageDropDown, targetPage)
        sourceField.setValue(source)
        return this
    }


    @Step("Choose Elements From DropDown: {affiliate}, {targetPage}, {source}")
    fun setOptionForBanners(
        affiliate: String,
        targetPage: String,
        source: String
    ): MediaPage {
        log.info("Choose Elements From DropDown: $affiliate, $targetPage, $source")
        chooseElementFromDropDown(trackingCodeSection(dataId).`$x`(".//select[contains(@id,'affiliate')]"), affiliate)
        chooseElementFromDropDown(targetPageDropDown, targetPage)
        sourceField.setValue(source)
        return this
    }

    @Step("Choose Elements From DropDown: {affiliate}, {language}, {targetPage}, {source}")
    fun setOptionForMailers(
        affiliate: String,
        language: String,
        targetPage: String,
        source: String
    ): MediaPage {
        log.info("Choose Elements From DropDown for TrackLink Generator: $affiliate, $language, $targetPage, $source")
        chooseElementFromDropDown(trackingCodeSection(dataId).`$x`(".//select[contains(@id,'affiliate')]"), affiliate)
        chooseElementFromDropDown(mailersLanguageDropDown, language)
        chooseElementFromDropDown(targetPageDropDown, targetPage)
        sourceField.setValue(source)
        return this
    }

    @Step("Filter elements in Banners Module: {brand}, {size}, {language}")
    fun useBannersFilter(brand: String, size: String = "All", language: String): MediaPage {
        log.info("Filter elements in Banners Module: $brand, $size, $language")
        chooseElementFromDropDown(brandFilterDropDown, brand)
        chooseElementFromDropDown(sizeFilterDropDown, size)
        chooseElementFromDropDown(languageFilterDropDown, language)
        useFilter()
        return this
    }

    @Step("Filter elements in Mailers Module: {brand}, {language}")
    fun useMailersFilter(brand: String, language: String): MediaPage {
        log.info("Filter elements in Mailers Module: $brand, $language")
        chooseElementFromDropDown(brandFilterDropDown, brand)
        chooseElementFromDropDown(languageFilterDropDown, language)
        useFilter()
        return this
    }

    @Step("Click filter submit button")
    private fun useFilter() = filterSearchButton.click()

    @Step("Get text from clipBoard using clipBoard button")
    fun getTextFromClipBoard(): String {
        log.info("Get text from clipBoard using clipBoard button")
//        if (trackLinkGenerator) copyToClipBoardButtonTrackLinkGen.click()
        copyToClipBoardButton.click()
        return clipboard().text
    }

    @Step("Open redirect link: {link}, in new tab")
    fun openGeneratedLinkInNewTab(link: String): MediaPage {
        log.info("Open redirect link: $link, in new tab")
        switchTo().newWindow(WindowType.TAB)
        open(link)
        return this
    }


    @Step("Check redirect link - {link} open and url got an appropriate language - {language}")
    fun checkRedirectSucceed(link: String, language: String) {
        log.info("Check redirect link - $link open and url got an appropriate language - $language")
        waitForJStoLoad
        val currentPageUrl = WebDriverRunner.getWebDriver().currentUrl
        assertContains(
            currentPageUrl,
            """^(https://www\..*$link).*\.com(/$language/)${'$'}""".toRegex(), "URL does not correspond"
        )
    }

    @Step("Expand tracking code form")
    fun expandTrackingCode(): MediaPage {
        log.info("Expand tracking code form")
        runBlocking { delay(3000) }
        dataId = trackingCode.`$x`(".//..").getAttribute("data-id")!!
        trackingCode.click()
        trackingCodeSection(dataId).shouldNotHave(Condition.attribute("style", "display: none;"))
        return this
    }

    @Step("Get selected language from form")
    fun getSelectedLanguage(): String {
        log.info("Get selected language from form")
        return trackingCodeSection(dataId).`$x`(".//label[contains(text(), 'Language')]//..//select").selectedText
    }

    @Step("Check language filtered correctly")
    fun checkFilteredLanguage(language: String): MediaPage {
        log.info("Check language filtered correctly")
        assertEquals(language, getSelectedLanguage())
        return this
    }

    @Step("Copy generated text from textarea")
    fun getTextFromMediaCodeArea(trackLinkGenerator: Boolean): String {
        log.info("Copy generated text from textarea")
        return if (trackLinkGenerator) {
            mediaCodeArea.scrollIntoView(false).click()
            mediaCodeArea.sendKeys(Keys.chord(Keys.CONTROL, "A"))
            mediaCodeArea.sendKeys(Keys.chord(Keys.CONTROL, "C"))
            clipboard().text
        } else {
            trackingCodeSection(dataId).`$x`(".//textarea[contains(@id, 'link_code')]").scrollIntoView(false).click()
            runBlocking { delay(1000) }
            trackingCodeSection(dataId).`$x`(".//textarea[contains(@id, 'link_code')]")
                .sendKeys(Keys.chord(Keys.CONTROL, "A"))
            trackingCodeSection(dataId).`$x`(".//textarea[contains(@id, 'link_code')]")
                .sendKeys(Keys.chord(Keys.CONTROL, "C"))
            clipboard().text
        }
    }

    @Step("Get text from Link area")
    fun getTextFromLinkArea(trackLinkGenerator: Boolean): String {
        log.info("Get text from Link area")
        val clipBoardText = getTextFromClipBoard()
        return if (StringUtils.isNotBlank(clipBoardText)) {
            getTextFromClipBoard()
        } else {
            log.error("ClipBoard action is not working")
            getTextFromMediaCodeArea(trackLinkGenerator)
        }
    }
}

//        assertEquals(link, currentPageUrl, "URL does not correspond")
//^(https://www\.winuniquecasino).*[^/](/en/)${'$'}