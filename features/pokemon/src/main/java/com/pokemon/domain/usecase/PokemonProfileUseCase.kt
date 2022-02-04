package com.pokemon.domain.usecase

import com.pokemon.domain.repository.PokemonProfileRepository

internal class PokemonProfileUseCase(val repository: PokemonProfileRepository) {

    suspend fun fetchPokemonProfile(pokemon: String) = repository.fetchPokemons(pokemon)
    suspend fun getAllPokemons() = repository.getAllPokemons()
}