package com.pokemon.di

import com.pokemon.data.mapper.PokemonProfileResponseMapper
import com.pokemon.data.repository.PokemonProfileCachePolicyRepositoryImpl
import com.pokemon.data.repository.PokemonProfileRepositoryImpl
import com.pokemon.domain.repository.PokemonProfileCachePolicyRepository
import com.pokemon.domain.repository.PokemonProfileRepository
import com.pokemon.domain.usecase.PokemonProfileUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val pokemonDataModule: Module = module {
    single { providePokemonProfileDB(androidApplication()) }
    single { providePokemonProfileDao(db = get()) }
    single { provideRetrofit() }
    single { provideService(retrofit = get()) }
    single<PokemonProfileCachePolicyRepository>{
        PokemonProfileCachePolicyRepositoryImpl(
            dao = get()
        )
    }
    single<PokemonProfileRepository> {
        PokemonProfileRepositoryImpl(
            service = get(),
            cache = get(),
            mapper = get()
        )
    }
    factory { PokemonProfileResponseMapper() }
}

val pokemonDomainModule: Module = module {
    factory {
        PokemonProfileUseCase(repository = get())
    }
}