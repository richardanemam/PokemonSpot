package com.pokemon.data.repository

import com.pokemon.data.extensions.isBiggerThan
import com.pokemon.data.extensions.toEntity
import com.pokemon.data.extensions.toModel
import com.pokemon.data.localdatasource.PokemonProfileDao
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.domain.repository.PokemonProfileCachePolicyRepository

private const val MAX_RECORDS = 10

internal class PokemonProfileCachePolicyRepositoryImpl(private val dao: PokemonProfileDao) :
    PokemonProfileCachePolicyRepository {

    override suspend fun put(pokemonProfile: PokemonProfile) {
        delete()
        dao.insert(pokemonProfile.toEntity())
    }

    override suspend fun delete() {
        if (dao.getNumberOfRecords().isBiggerThan(MAX_RECORDS)) {
            dao.delete(dao.getOldestPokemon())
        }
    }

    override suspend fun getAllPokemonsCached(): List<PokemonProfile> {
        val pokemonList = mutableListOf<PokemonProfile>()
        dao.getAllPokemons().forEach {
            pokemonList.add(it.toModel())
        }
        return pokemonList
    }
}