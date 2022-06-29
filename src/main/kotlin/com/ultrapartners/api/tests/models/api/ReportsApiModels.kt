package com.ultrapartners.api.tests.models.api

import com.fasterxml.jackson.annotation.JsonProperty

data class TokenResponse(
    @JsonProperty("token_type")
    var tokenType: String,
    @JsonProperty("expires_in")
    var expiresIn: String,
    @JsonProperty("access_token")
    var accessToken: String,
    @JsonProperty("refresh_token")
    var refreshToken: String
)

data class ReportApiRequest(
    var fromDate: String,
    var toDate: String,
)

data class CountryReportItem(
    var country: String? = null,
    var verifiedMembers: Any? = null,
    var payout: Any? = null,
    var sharedFees: Any? = null,
    var deposits: Any? = null,
    var netGaming: Any? = null,
    var trackerId: Any? = null,
    var newDepositors: Any? = null,
    var newMembers: Any? = null,
    var uniqueClicks: Any? = null,
    var plan: String? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null
)

data class DailyReportItem(
    var date: String? = null,
    var verifiedMembers: Any? = null,
    var payout: Any? = null,
    var sharedFees: Any? = null,
    var platform: String? = null,
    var deposits: Any? = null,
    var netGaming: Any? = null,
    var newDepositors: Any? = null,
    var newMembers: Any? = null,
    var uniqueClicks: Any? = null,
    var plan: String? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null
)

data class MediaReportItem(
    var verifiedMembers: Any? = null,
    var payout: Any? = null,
    var media: String? = null,
    var sharedFees: Any? = null,
    var deposits: Any? = null,
    var netGaming: Any? = null,
    var trackerId: Any? = null,
    var newDepositors: Any? = null,
    var newMembers: Any? = null,
    var uniqueClicks: Any? = null,
    var plan: String? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null
)

data class PlatformReportItem(
    var verifiedMembers: Any? = null,
    var payout: Any? = null,
    var sharedFees: Any? = null,
    var platform: String? = null,
    var deposits: Any? = null,
    var netGaming: Any? = null,
    var trackerId: Any? = null,
    var newDepositors: Any? = null,
    var newMembers: Any? = null,
    var uniqueClicks: Any? = null,
    var plan: String? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null
)

data class SourceReportItem(
    var src: String? = null,
    var verifiedMembers: Any? = null,
    var payout: Any? = null,
    var sharedFees: Any? = null,
    var deposits: Any? = null,
    var netGaming: Any? = null,
    var trackerId: Any? = null,
    var newDepositors: Any? = null,
    var newMembers: Any? = null,
    var uniqueClicks: Any? = null,
    var plan: String? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null
)

data class SubReportItem(
    var sub: String? = null,
    var verifiedMembers: Any? = null,
    var payout: Any? = null,
    var sharedFees: Any? = null,
    var deposits: Any? = null,
    var netGaming: Any? = null,
    var trackerId: Any? = null,
    var newDepositors: Any? = null,
    var newMembers: Any? = null,
    var uniqueClicks: Any? = null,
    var plan: String? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null
)

data class TrackerReportItem(
    var trackerId: Any? = null,
    var newDepositors: Any? = null,
    var verifiedMembers: Any? = null,
    var newMembers: Any? = null,
    var payout: Any? = null,
    var uniqueClicks: Any? = null,
    var sharedFees: Any? = null,
    var plan: String? = null,
    var deposits: Any? = null,
    var grossClicks: Any? = null,
    var withdraw: Any? = null,
    var netGaming: Any? = null
)

enum class ReportType(var path: String) {
    COUNTRY("country"),
    DAILY("daily"),
    MEDIA("media"),
    PLATFORM("platform"),
    SOURCE("source"),
    SUB("sub"),
    TRACKER("tracker")
}

