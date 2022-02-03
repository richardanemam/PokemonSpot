package com.pokemon.domain.repository

import com.pokemon.domain.model.PokemonProfile

interface PokemonProfileCachePolicyRepository {
    fun put(pokemonProfile: PokemonProfile)
    fun getAllPokemonsCached(): List<PokemonProfile>
}