package com.pokemon.presentation.activity.profileactivity.viewmodel

import com.common.core.arch.UIState
import com.pokemon.domain.model.PokemonProfile

data class PokemonProfileState(
    val pokemonProfileList: List<PokemonProfile> = listOf(),
    val isLoading: Boolean = false,
    val isPokemonProfileListEmpty: Boolean = true,
    val message: String? = null
) : UIState {

    fun setPokemonProfileList(pokemonProfileList: List<PokemonProfile>) =
        this.copy(pokemonProfileList = pokemonProfileList)

    fun setLoading(isLoading: Boolean) = this.copy(isLoading = isLoading)

    fun setPokemonProfileListState(isPokemonProfileListEmpty: Boolean) =
        this.copy(isPokemonProfileListEmpty = isPokemonProfileListEmpty)

    fun setErrorMessage(message: String) = this.copy(message = message)
}