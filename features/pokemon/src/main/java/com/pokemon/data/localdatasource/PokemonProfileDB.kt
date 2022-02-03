package com.pokemon.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonProfileEntity::class], version = 1)
abstract class PokemonProfileDB: RoomDatabase() {
    abstract fun pokemonProfileDao(): PokemonProfileDao
}