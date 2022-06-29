package com.ultrapartners.api.tests.reports

import com.ultrapartners.api.tests.BaseApiTest
import com.ultrapartners.api.tests.apis.getAuthToken
import com.ultrapartners.api.tests.apis.reportRequest
import com.ultrapartners.api.tests.assertions.*
import com.ultrapartners.api.tests.models.api.*
import com.ultrapartners.ui.tests.Props
import io.qameta.allure.Feature
import io.qameta.allure.Severity
import io.qameta.allure.SeverityLevel
import org.aeonbits.owner.ConfigFactory
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import ui.tests.data.API
import java.time.LocalDateTime

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Severity(SeverityLevel.CRITICAL)
@DisplayName("Reports API Tests")
@Tag(API)
class ReportsApiTests : BaseApiTest() {
    private val props = ConfigFactory.create(Props::class.java)
    private val df = LocalDateTime.now().minusDays(100)
    private val dt = LocalDateTime.now().minusDays(1)
    private lateinit var token: TokenResponse

    @BeforeAll
    fun getToken() {
        token = getAuthToken(props.ultraPartnersApiLogin(), props.ultraPartnersApiPassword())
            .andVerifyResponseIs(200, TokenResponse(NOT_EMPTY, NOT_EMPTY, NOT_EMPTY, NOT_EMPTY))
    }

    @ParameterizedTest(name = "{index}: {0} {displayName}")
    @MethodSource("reportsTestData")
    @Feature("Reports API")
    @DisplayName("Report Test")
    fun parametrizedReportsApiTest(reportType: ReportType, dataClass: Any?) {
        reportRequest(
            reportType.path,
            token = token.accessToken,
            fromDate = df,
            toDate = dt
        ).andVerifyListResponseIs(200, dataClass)
    }

    companion object {
        @JvmStatic
        fun reportsTestData() = listOf(
            Arguments.of(
                ReportType.COUNTRY,
                CountryReportItem(
                    NOT_EMPTY, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS,
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, NOT_EMPTY, ANY_DIGITS, ANY_DIGITS
                )
            ),
            Arguments.of(
                ReportType.DAILY,
                DailyReportItem(
                    NOT_EMPTY, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_STRING_OR_NULL, ANY_DIGITS,
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_STRING_OR_NULL, ANY_DIGITS, ANY_DIGITS
                )
            ),
            Arguments.of(
                ReportType.MEDIA,
                MediaReportItem(
                    ANY_DIGITS, ANY_DIGITS, NOT_EMPTY, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS,
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, NOT_EMPTY, ANY_DIGITS, ANY_DIGITS
                )
            ),
            Arguments.of(
                ReportType.PLATFORM,
                PlatformReportItem(
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_STRING_OR_NULL, ANY_DIGITS, ANY_DIGITS,
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, NOT_EMPTY, ANY_DIGITS, ANY_DIGITS
                )
            ),
            Arguments.of(
                ReportType.SOURCE,
                SourceReportItem(
                    EMPTY, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS,
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, NOT_EMPTY, ANY_DIGITS, ANY_DIGITS
                )
            ),
            Arguments.of(
                ReportType.SUB,
                SubReportItem(
                    EMPTY, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS,
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, NOT_EMPTY, ANY_DIGITS, ANY_DIGITS
                )
            ),
            Arguments.of(
                ReportType.TRACKER,
                TrackerReportItem(
                    ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS,
                    ANY_DIGITS, ANY_STRING_OR_NULL, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS, ANY_DIGITS,
                )
            ),
        )
    }
}