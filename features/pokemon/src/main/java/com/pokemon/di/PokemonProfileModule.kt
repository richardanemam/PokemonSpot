package com.pokemon.di

import com.pokemon.data.mapper.PokemonProfileResponseMapper
import com.pokemon.data.repository.PokemonProfileCachePolicyRepositoryImpl
import com.pokemon.data.repository.PokemonProfileRepositoryImpl
import com.pokemon.domain.repository.PokemonProfileCachePolicyRepository
import com.pokemon.domain.repository.PokemonProfileRepository
import com.pokemon.domain.usecase.PokemonProfileUseCase
import com.pokemon.presentation.activity.profileactivity.viewmodel.PokemonProfileViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val pokemonDataModule: Module = module {
    single { providePokemonProfileDB(androidApplication()) }
    factory { providePokemonProfileDao(db = get()) }
    factory { provideRetrofit() }
    factory { provideService(retrofit = get()) }
    factory<PokemonProfileCachePolicyRepository> {
        PokemonProfileCachePolicyRepositoryImpl(
            dao = get()
        )
    }
    factory<PokemonProfileRepository> {
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

val pokemonPresentationModule: Module = module {
    viewModel {
        PokemonProfileViewModel(
            useCase = get(),
            resourceProvider = get()
        )
    }
}