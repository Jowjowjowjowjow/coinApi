package com.test.coinapi.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val format = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'"
val locale = Locale("pt", "BR")

fun String.toDate(): Date? {
    return SimpleDateFormat(
        format, locale
    ).parse(this)
}