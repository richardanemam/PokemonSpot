package com.example.pokemonspot.mainapplication

import android.app.Application
import com.common.core.di.resourcesModule
import com.pokemon.di.pokemonDataModule
import com.pokemon.di.pokemonDomainModule
import com.pokemon.di.pokemonPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    pokemonDataModule,
                    pokemonDomainModule,
                    pokemonPresentationModule,
                    resourcesModule
                )
            )
        }
    }
}