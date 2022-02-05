package com.pokemon.data.repository

import com.pokemon.data.api.PokemonProfileService
import com.pokemon.data.mapper.PokemonProfileResponseMapper
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.domain.repository.PokemonProfileCachePolicyRepository
import com.pokemon.domain.repository.PokemonProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PokemonProfileRepositoryImpl(
    private val service: PokemonProfileService,
    private val cache: PokemonProfileCachePolicyRepository,
    private val mapper: PokemonProfileResponseMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonProfileRepository {

    override suspend fun fetchPokemons(pokemon: String) {
        withContext(dispatcher) {
            val pokemonProfile = service.getPokemon(pokemon = pokemon)
            if (pokemonProfile.isSuccessful) {
                with(pokemonProfile) {
                    body()?.let { mapper.map(it) }?.let { cache.put(it) }
                }
            }
        }
    }


    override suspend fun getAllPokemons(): List<PokemonProfile> = cache.getAllPokemonsCached()
}