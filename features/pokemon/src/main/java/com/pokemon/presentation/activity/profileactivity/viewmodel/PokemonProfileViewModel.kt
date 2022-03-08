package com.pokemon.presentation.activity.profileactivity.viewmodel

import androidx.lifecycle.viewModelScope
import com.common.core.arch.viewmodel.BaseViewModel
import com.common.core.resources.ResourceProvider
import com.common.extensions.toApiSearchPattern
import com.pokemon.R
import com.pokemon.domain.extensions.isPokemonCached
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.domain.usecase.PokemonProfileUseCase
import kotlinx.coroutines.launch

internal class PokemonProfileViewModel(
    private val useCase: PokemonProfileUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<PokemonProfileState, PokemonProfileAction>(PokemonProfileState()) {

    init {
        displayCachedData()
    }

    private fun displayCachedData() {
        viewModelScope.launch {
            val cache = useCase.getAllPokemons()
            if (cache.isNotEmpty()) {
                setPokemonProfileState(cache)
            }
        }
    }

    fun fetchPokemon(pokemon: String) {
        viewModelScope.launch {
            setState { it.setLoading(true) }
            if(isPokemonCached(pokemon).not()) {
                useCase.fetchPokemonProfile(pokemon)
                setPokemonAvailabilityState(pokemon)
            }
        }
    }

    fun searchPokemon(pokemon: String?) {
        when (pokemon) {
            null -> {
                setState {
                    it.setErrorMessage(
                        resourceProvider.getString(R.string.message_pokemon_internet_unavailable)
                    )
                }
            }
            else -> sendAction { PokemonProfileAction.FetchPokemon(pokemon.toApiSearchPattern()) }
        }
    }

    fun navigateToDetails(pokemonDetails: PokemonProfile) {
        sendAction { PokemonProfileAction.NavigateToDetails(pokemonDetails) }
    }

    private suspend fun isPokemonCached(pokemon: String): Boolean {
        if (pokemon.isPokemonCached(useCase.getAllPokemons())) {
            setMessageState(
                resourceProvider.getString(R.string.message_pokemon_already_on_list)
            )
            return true
        }
        return false
    }

    private suspend fun setPokemonAvailabilityState(pokemon: String) {
        val pokemons = useCase.getAllPokemons()
        when {
            pokemon.isPokemonCached(pokemons) -> {
                setPokemonProfileState(pokemons)
            }
            else -> {
                setMessageState(resourceProvider.getString(R.string.message_pokemon_not_found))
            }
        }
    }

    private fun setPokemonProfileState(pokemons: List<PokemonProfile>) {
        setState {
            it
                .setPokemonProfileList(pokemons)
                .setLoading(false)
                .setErrorMessage(null)
        }
    }

    private fun setMessageState(message: String?) {
        setState {
            it
                .setLoading(false)
                .setErrorMessage(message)
        }
    }
}