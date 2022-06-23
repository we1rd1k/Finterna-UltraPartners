package com.ultrapartners.ui.tests.affiliate.affiliatesteps

import com.codeborne.selenide.Selenide
import com.ultrapartners.ui.tests.data.TRACKING_LINK_GENERATOR
import com.ultrapartners.ui.tests.pages.affiliate.MediaPage
import com.ultrapartners.ui.tests.utils.getLinkFromClipBoard
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun `Generate Track Link`(
    affiliate: String,
    brand: String,
    language: String,
    targetPage: String,
    source: String
) {
    MediaPage()
        .weAreOnMediaPage()
        .setOptionForTrackLinkGenerator(affiliate, brand, language, targetPage, source)
}

fun `Get generated link from clipBoard and check redirect succeed`(
    link: String,
    language: String,
    trackLinkGenerator: Boolean
) {
    val generatedLink = getLinkFromClipBoard(MediaPage().weAreOnMediaPage().getTextFromLinkArea(trackLinkGenerator))
    MediaPage()
        .openGeneratedLinkInNewTab(generatedLink)
        .checkRedirectSucceed(link, language)
}

fun `Switch to module`(moduleName: String) {
    MediaPage()
        .switchToModule(moduleName)
}

fun `Submit filter for Banners`(brand: String, size: String = "All", language: String) {
    MediaPage()
        .useBannersFilter(brand, size, language)
        .expandTrackingCode()
        .checkFilteredLanguage(language)
}

fun `Submit filter for Mailers`(brand: String, language: String = "All") {
    MediaPage()
        .useMailersFilter(brand, language)
        .expandTrackingCode()
}

fun `Generate Track Link for Banner`(
    affiliate: String,
    targetPage: String,
    source: String
) {
    MediaPage()
        .setOptionForBanners(affiliate, targetPage, source)
}

fun `Generate Track Link for Mailer`(
    affiliate: String,
    language: String,
    targetPage: String,
    source: String
) {
    MediaPage()
        .setOptionForMailers(affiliate, language, targetPage, source)
}

fun `Check ClipBoard Button`(moduleName: String): String {
    val mediaPage = MediaPage()
    runBlocking { delay(2000) }
    mediaPage
        .switchToModule(moduleName)
    Selenide.clipboard().text = ""
    return if (moduleName != TRACKING_LINK_GENERATOR) {
        mediaPage
            .expandTrackingCode()
            .getTextFromClipBoard()
    } else {
        mediaPage
            .getTextFromClipBoard()
    }
}
