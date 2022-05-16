package com.ultrapartners.api.tests.models

import com.fasterxml.jackson.annotation.JsonProperty

data class AppHealthResponse(
    var mysql: MySqlResponse? = MySqlResponse(),
    var redis: RedisResponse? = RedisResponse(),
    @JsonProperty("api_integrations")
    var apiIntegrations: ApiIntegrResponse? = ApiIntegrResponse()
)

data class MySqlResponse(
    var status: Any? = true,
    var description: Any? = "",
    var data: MySqlData? = MySqlData(),
)

data class MySqlData(
    var db: Any? = true,
    var source_db: Any? = true,
    var copy_db: Any? = true,
    var affiliate_generic_db: Any? = true,
    var unique_db: Any? = true,
    var short_db: Any? = true,
    var tmp_db: Any = true,
)

data class RedisResponse(
    var status: Any? = true,
    var description: Any? = "",
    var data: List<Any>? = emptyList(),
)

data class ApiIntegrResponse(
    var status: Any? = true,
    var description: Any? = "",
    var data: ApiIntegrationData? = ApiIntegrationData(),
)

data class ApiIntegrationData(
    var agdLink: Boolean? = true,
    var gpwaLink: Boolean? = true,
)