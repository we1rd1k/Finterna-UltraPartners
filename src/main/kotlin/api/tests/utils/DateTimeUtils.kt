package api.tests.utils

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


fun currentDate(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDateTime.now(ZoneId.of("Europe/Moscow")).format(formatter)
}

fun dateFormatter(date: String): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    val oldDate = formatter.parse(date)
    formatter.applyPattern("yyyy-MM-dd")
    return formatter.format(oldDate)
}

fun getUTC(): String =
    DateTimeFormatter
        .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS").withZone(ZoneId.of("UTC"))
        .format(ZonedDateTime.now())

fun dateToISO8601(date: Date = Date(), timeZone: String? = "Europe/Moscow"): String? {
    val tz = TimeZone.getTimeZone(timeZone)
    val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    df.timeZone = tz
    return df.format(date)
}