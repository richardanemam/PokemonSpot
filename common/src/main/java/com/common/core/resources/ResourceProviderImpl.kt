package com.common.core.resources

import android.content.Context

class ResourceProviderImpl(private val context: Context): ResourceProvider {
    override fun getString(resId: Int): String = context.resources.getString(resId)
}