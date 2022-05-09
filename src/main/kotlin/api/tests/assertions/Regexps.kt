package api.tests.assertions

import api.tests.utils.currentDate

const val ANY_UUID = "regexp: [0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"
const val ANY_DIGITS = "regexp: [\\d]+"
const val NOT_EMPTY = "regexp: ^(?!\\s*\$).+"
const val ANY_STRING = "regexp: .*"
const val GROUPIB_LOGIN_HASH = "regexp: [0-9a-h]{64}"
const val ANY_URL = "regexp: ^http[s]?:(?!\\s*\$).+"
val IMAGE_CREATION_DATE = "regexp: ${currentDate()}T\\d{2}:\\d{2}:\\d{2}.\\d+Z"
val TIMESTAMP = "regexp: ${currentDate()}T\\d{2}:\\d{2}:\\d{2}.\\d+Z"
val CURRENT_DATE = "regexp: ${currentDate()}T\\d{2}:\\d{2}:\\d{2}Z"
const val ANY_ACCOUNT_NUMBER = "regexp: \\d{5}-\\d{4}-\\d{7}"
const val SMALL_ICON_URL = "regexp: ^http[s]?:(?!\\s*\$).+[\\d+32.png]"