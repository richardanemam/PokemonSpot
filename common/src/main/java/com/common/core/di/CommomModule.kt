package com.common.core.di

import com.common.core.resources.ResourceProvider
import com.common.core.resources.ResourceProviderImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val resourcesModule: Module = module {
    single <ResourceProvider>{
        ResourceProviderImpl(androidApplication().applicationContext)
    }
}