package com.pokemon.presentation.activity.pokemondetails.viewmodel

import com.common.core.arch.UIAction
import com.pokemon.domain.model.PokemonProfile

sealed class PokemonDetailsAction: UIAction {
    data class DisplayPokemonInfo(val pokemon: PokemonProfile?): PokemonDetailsAction()
}