package com.pokemon.data.mapper

import com.pokemon.data.model.MovesResponse
import com.pokemon.data.model.PokemonAbilitiesResponse
import com.pokemon.data.model.PokemonProfileResponse
import com.pokemon.domain.model.PokemonProfile

private const val FIRST_TYPE = 0

internal class PokemonProfileResponseMapper {

    fun map(pokemonProfileResponse: PokemonProfileResponse): PokemonProfile {
        return with(pokemonProfileResponse) {
            PokemonProfile(
                name = name,
                type = types?.get(FIRST_TYPE)?.type?.name,
                imageUrl = sprites?.other?.officialArtwork?.frontDefault,
                weight = weight,
                height = height,
                baseExperience = baseExperience,
                abilities = mapAbility(abilities) ,
                moves = mapMoves(moves)
            )
        }
    }

    private fun mapAbility(abilities: List<PokemonAbilitiesResponse>?): List<String> {
        return abilities?.let {
            val list = mutableListOf<String>()
            repeat(it.size) { position ->
                it[position].ability?.name?.let { ability -> list.add(ability) }
            }
            list
        } ?: listOf()
    }

    private fun mapMoves(movesList: List<MovesResponse>?): List<String> {
        return movesList?.let { moves ->
            val list = mutableListOf<String>()
            repeat(moves.size) { position ->
                movesList[position].move?.name?.let { list.add(it) }
            }
            list
        } ?: listOf()
    }
}