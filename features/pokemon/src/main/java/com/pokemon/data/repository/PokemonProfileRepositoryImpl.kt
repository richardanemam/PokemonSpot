package com.pokemon.data.repository

import com.pokemon.data.api.PokemonProfileService
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.domain.repository.PokemonProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonProfileRepositoryImpl(
    val service: PokemonProfileService,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
): PokemonProfileRepository {

    override suspend fun fetchPokemons(pokemon: String) {
        withContext(dispatcher) {
            val pokemonProfile = service.getPokemon(pokemon = pokemon)
        }
    }


    override suspend fun getAllPokemons(): List<PokemonProfile> {
        TODO("Not yet implemented")
    }
}