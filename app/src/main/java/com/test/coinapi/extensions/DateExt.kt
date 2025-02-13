package com.test.coinapi.extensions

import java.text.SimpleDateFormat
import java.util.Date


const val readableFormat = "dd/MM/yyyy HH:mm:ss"

fun Date?.format(): String? {
    return this?.let {
        SimpleDateFormat(
            readableFormat, locale
    ).format(it)
    }
}