package com.common.extensions

import java.util.*

fun String.toApiSearchPattern(): String = this.lowercase(Locale.getDefault()).trim()

fun List<String>.toCustomString(): String {
    val text = ""
    if (!this.isNullOrEmpty()) {
        for (items in this) {
            text.plus(items).plus("\n")
        }
    }

    return text
}