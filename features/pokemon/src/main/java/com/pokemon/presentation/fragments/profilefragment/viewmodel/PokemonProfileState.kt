package com.pokemon.presentation.fragments.profilefragment.viewmodel

import com.common.core.arch.UIState
import com.pokemon.domain.model.PokemonProfile

data class PokemonProfileState(
    val pokemonProfileList: MutableList<PokemonProfile> = mutableListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
): UIState {

    fun setPokemonProfileList(pokemonProfileList: MutableList<PokemonProfile>) =
        this.copy(pokemonProfileList = pokemonProfileList)

    fun setLoading(isLoading: Boolean) = this.copy(isLoading = isLoading)

    fun setErrorMessage(errorMessage: String) = this.copy(errorMessage = errorMessage)
}