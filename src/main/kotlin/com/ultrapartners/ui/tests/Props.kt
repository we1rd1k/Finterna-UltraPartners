package com.ultrapartners.ui.tests

import org.aeonbits.owner.Config
import org.aeonbits.owner.Config.LoadPolicy
import org.aeonbits.owner.Config.Sources

@LoadPolicy(Config.LoadType.MERGE)
@Sources("system:properties", "system:env", "file:src/main/resources/config.properties")
interface Props : Config {
    @Config.Key("ultraPartnersDev.url")
    fun ultraPartnersUrl(): String

    @Config.Key("login")
    fun login(): String

    @Config.Key("password")
    fun password(): String
}