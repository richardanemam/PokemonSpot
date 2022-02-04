package com.pokemon.domain.repository

import com.pokemon.domain.model.PokemonProfile

interface PokemonProfileCachePolicyRepository {
    suspend fun put(pokemonProfile: PokemonProfile)
    suspend fun delete()
    suspend fun getAllPokemonsCached(): List<PokemonProfile>
}