package com.ultrapartners.ui.tests.data

import org.openqa.selenium.Cookie

//Side Menu
const val DASHBOARD = "Dashboard"
const val SETTINGS = "Settings"
const val REPORTS = "Reports"
const val MEDIA = "Media"
const val PAYMENTS = "Payments"
const val NEWS = "News"
const val SUPPORT = "Support"

//Media Links
const val TRACKING_LINK_GENERATOR = "Tracking Link Generator"
const val BANNERS = "Banners"
const val LOGOS = "Logos"
const val MAILERS = "Mailers"
const val FREE_GAMES = "Free games"

//Languages

const val ENGLISH = "English"
const val FRENCH = "French"
const val GERMAN = "German"
const val ITALIAN = "Italian"
const val SPANISH = "Spanish"
const val JAPANESE = "Japanese"

//Brands
//const val UNIQUE_CASINO = "https://www.winuniquecasino-club.com"
//const val MA_CHANCE = "https://www.winmachancecasino-slots.com"
//const val VEGAS_PLUS = "https://www.winvegasplus-live.com"

data class Brand(var name: String, var url: String)
data class Language(var langName: String, var langUrl: String)

enum class Brands(val getBrand: Brand) {
    UNIQUE_CASINO(Brand("Unique Casino", "winunique")),
    MA_CHANCE(Brand("MaChance", "winmachance")),
    VEGAS_PLUS(Brand("VegasPlus", "vegasplus"))
}

enum class Languages(val getLanguage: Language) {
    ENGLISH(Language("English", "en")),
    FRENCH(Language("French", "fr")),
    GERMAN(Language("German", "de")),
    ITALIAN(Language("Italian", "it")),
    SPANISH(Language("Spanish", "es")),
    JAPANESE(Language("Japanese", "ja")),
    SWEDISH(Language("Swedish", "sv")),
    PORTUGUESE(Language("Portuguese", "pt")),
    NORWEGIAN(Language("Norwegian", "no"))
}

lateinit var dataId: String
var cookies: Set<Cookie> = mutableSetOf()