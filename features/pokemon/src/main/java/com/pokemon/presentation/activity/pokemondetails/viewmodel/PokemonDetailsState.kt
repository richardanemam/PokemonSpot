package com.pokemon.presentation.activity.pokemondetails.viewmodel

import com.common.core.arch.UIState
import com.pokemon.domain.model.PokemonProfile

data class PokemonDetailsState(
    val pokemonInfo: PokemonProfile? = null,
    val errorMessage: String? = null): UIState