package com.pokemon.common

import com.pokemon.domain.model.PokemonProfile

internal fun getPokemonProfileList() = listOf(
    PokemonProfile(
        name = "charizard",
        type = "fire",
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",
        weight = 905,
        height = 17,
        baseExperience = 240,
        abilities = listOf("blaze", "solar-power"),
        moves = listOf("mega-punch", "fly")
    )
)

internal fun getPokemonProfile() = PokemonProfile(
    name = "charizard",
    type = "fire",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",
    weight = 905,
    height = 17,
    baseExperience = 240,
    abilities = listOf("blaze", "solar-power"),
    moves = listOf("mega-punch", "fly")
)