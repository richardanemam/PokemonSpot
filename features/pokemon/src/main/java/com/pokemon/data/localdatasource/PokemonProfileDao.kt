package com.pokemon.data.localdatasource

import androidx.room.*
import com.pokemon.domain.model.PokemonProfile

@Dao
interface PokemonProfileDao {

    @Query("SELECT * FROM pokemon")
    fun getAllPokemons(): List<PokemonProfileEntity>

    @Query("SELECT count(*) FROM pokemon")
    suspend fun getNumberOfRecords(): Int

    @Query("SELECT * FROM pokemon LIMIT 1")
    suspend fun getOldestPokemon(): PokemonProfileEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonProfileEntity)

    @Delete
    fun delete(user: PokemonProfileEntity)
}