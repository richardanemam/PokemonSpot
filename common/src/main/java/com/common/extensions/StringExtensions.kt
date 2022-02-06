package com.common.extensions

import java.util.*

fun String.toApiSearchPattern(): String = this.lowercase(Locale.getDefault()).trim()