package com.pokemon.domain.extensions

import com.pokemon.domain.model.PokemonProfile

fun String.isPokemonCached(pokemonList: List<PokemonProfile>) =
    pokemonList.find { it.name == this } != null