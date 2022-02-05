package com.pokemon.data.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PokemonProfileEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonProfileDB: RoomDatabase() {
    abstract fun pokemonProfileDao(): PokemonProfileDao
}