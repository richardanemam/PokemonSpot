package com.pokemon.domain.repository

import com.pokemon.domain.model.PokemonProfile

interface PokemonProfileRepository {
    suspend fun fetchPokemons(pokemon: String)
    suspend fun getAllPokemons(): List<PokemonProfile>
}