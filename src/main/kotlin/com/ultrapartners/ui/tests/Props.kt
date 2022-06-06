package com.ultrapartners.ui.tests

import org.aeonbits.owner.Config
import org.aeonbits.owner.Config.LoadPolicy
import org.aeonbits.owner.Config.Sources

@LoadPolicy(Config.LoadType.MERGE)
@Sources("system:properties", "system:env", "file:src/main/resources/config.properties")
interface Props : Config {

    fun ultraPartnersUrl(): String

    fun affiliateLogin(): String

    fun adminLogin(): String

    fun password(): String

    fun selenoidUrl(): String
}