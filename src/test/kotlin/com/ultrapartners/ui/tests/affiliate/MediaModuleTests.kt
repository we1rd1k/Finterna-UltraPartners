package com.ultrapartners.ui.tests.affiliate

import com.codeborne.selenide.Selenide
import com.ultrapartners.ui.tests.BaseTest
import com.ultrapartners.ui.tests.Props
import com.ultrapartners.ui.tests.affiliate.affiliatesteps.*
import com.ultrapartners.ui.tests.data.*
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.aeonbits.owner.ConfigFactory
import org.assertj.core.api.SoftAssertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import org.junit.platform.commons.util.StringUtils
import ui.tests.data.CI
import ui.tests.data.REGRESS

@Tag(CI)
@Tag(REGRESS)
@Epic("Media Module")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Redirect Links Tests")
class MediaModuleTests : BaseTest() {

    private val props = ConfigFactory.create(Props::class.java)

    @BeforeEach
    fun setUp() {
        Selenide.clipboard().text = ""
        `Affiliate Login`(props.affiliateLogin(), props.password())
        `Go to Page`(MEDIA)
    }

    @Feature("Track Link Generator")
    @DisplayName("[Affiliate][Media]Tracking Link Generator - Redirect Tests")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("trackLinkData")
    @ParameterizedTest
    fun `Tracking Link Generator Test`(
        affiliate: String,
        brand: Brand,
        language: Language,
        targetPage: String,
        source: String
    ) {
        `Generate Track Link`(
            affiliate = affiliate,
            brand = brand.name,
            language = language.langName,
            targetPage = targetPage,
            source = source
        )
        `Get generated link from clipBoard and check redirect succeed`(
            brand.url,
            language.langUrl,
            trackLinkGenerator = true
        )
    }

    @Feature("Banners")
    @DisplayName("[Affiliate][Media]Banners - Redirect Tests")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("trackLinkData")
    @ParameterizedTest
    fun `Banners Test`(
        affiliate: String,
        brand: Brand,
        language: Language,
        targetPage: String,
        source: String
    ) {
        `Switch to module`(BANNERS)
        `Submit filter for Banners`(brand.name, language = language.langName)
        `Generate Track Link for Banner`(affiliate, targetPage, source)
        `Get generated link from clipBoard and check redirect succeed`(brand.url, language.langUrl, false)
    }

    @Feature("Mailers")
    @DisplayName("[Affiliate][Media]Mailers - Redirect Tests")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("trackLinkData")
    @ParameterizedTest
    fun `Mailers Test`(
        affiliate: String,
        brand: Brand,
        language: Language,
        targetPage: String,
        source: String
    ) {
        `Switch to module`(MAILERS)
        `Submit filter for Mailers`(brand.name)
        `Generate Track Link for Mailer`(affiliate, language.langName, targetPage, source)
        `Get generated link from clipBoard and check redirect succeed`(brand.url, language.langUrl, false)
    }

    @Feature("ClipBoard")
    @DisplayName("[Affiliate][Media]ClipBoard Button Tests")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    fun `Clipboard Button Test`(
    ) {
        val softly = SoftAssertions()
        softly.assertThat(StringUtils.isNotBlank(`Check ClipBoard Button`(TRACKING_LINK_GENERATOR)))
            .`as`("$TRACKING_LINK_GENERATOR Clipboard button").isTrue
        softly.assertThat(StringUtils.isNotBlank(`Check ClipBoard Button`(BANNERS)))
            .`as`("$BANNERS Clipboard button").isTrue
        softly.assertThat(StringUtils.isNotBlank(`Check ClipBoard Button`(MAILERS)))
            .`as`("$MAILERS Clipboard button").isTrue
        softly.assertAll()
    }

    companion object {
        @JvmStatic
        fun trackLinkData() = listOf(
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.ENGLISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.GERMAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.ITALIAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.SPANISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.SWEDISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.JAPANESE.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.NORWEGIAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.PORTUGUESE.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.UNIQUE_CASINO.getBrand, Languages.FRENCH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.MA_CHANCE.getBrand, Languages.ENGLISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.MA_CHANCE.getBrand, Languages.SPANISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.MA_CHANCE.getBrand, Languages.ITALIAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.MA_CHANCE.getBrand, Languages.FRENCH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.MA_CHANCE.getBrand, Languages.GERMAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.JAPANESE.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.SWEDISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.ENGLISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.GERMAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.ITALIAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.SPANISH.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.NORWEGIAN.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.PORTUGUESE.getLanguage, "Home Page", ""),
            of("37213 - Main", Brands.VEGAS_PLUS.getBrand, Languages.FRENCH.getLanguage, "Home Page", ""),
        )
    }
}