package com.pokemon.di

import android.app.Application
import androidx.room.Room
import com.pokemon.data.api.PokemonProfileService
import com.pokemon.data.localdatasource.PokemonProfileDB
import com.pokemon.data.localdatasource.PokemonProfileDao
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal fun providePokemonProfileDB(application: Application): PokemonProfileDB {
    return Room.databaseBuilder(
        application.applicationContext,
        PokemonProfileDB::class.java,
        "pokemon"
    ).fallbackToDestructiveMigration().build()
}

internal fun providePokemonProfileDao(db: PokemonProfileDB): PokemonProfileDao {
    return db.pokemonProfileDao()
}

internal fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/pokemon/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

internal fun provideService(retrofit: Retrofit) = retrofit.create(PokemonProfileService::class.java)
