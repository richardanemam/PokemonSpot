package com.pokemon.presentation.activity.profileactivity.viewmodel

import androidx.lifecycle.viewModelScope
import com.common.core.arch.viewmodel.BaseViewModel
import com.common.core.resources.ResourceProvider
import com.pokemon.R
import com.pokemon.domain.extensions.isPokemonCached
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.domain.usecase.PokemonProfileUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class PokemonProfileViewModel(
    private val useCase: PokemonProfileUseCase,
    private val resourceProvider: ResourceProvider,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<PokemonProfileState, PokemonProfileAction>(PokemonProfileState()) {

    fun fetchPokemon(pokemon: String) {
        viewModelScope.launch(dispatcher) {
            setLoadingState(isLoading = true)

            if (pokemon.isPokemonCached(useCase.getAllPokemons())) {
                setMessageState(resourceProvider.getString(R.string.message_pokemon_already_on_list))
            } else {
                useCase.fetchPokemonProfile(pokemon)
                setPokemonAvailabilityState(pokemon)
            }

        }.invokeOnCompletion {
            setLoadingState(isLoading = false)
        }
    }

    fun navigateToDetails(pokemonDetails: PokemonProfile) {
        sendAction {
            PokemonProfileAction.NavigateToDetails(pokemonDetails)
        }
    }

    private fun setMessageState(message: String) {
        setState { it.setErrorMessage(message) }
    }

    private fun setLoadingState(isLoading: Boolean) {
        setState { it.setLoading(isLoading) }
    }

    private suspend fun setPokemonAvailabilityState(pokemon: String) {
        val pokes = useCase.getAllPokemons()
        when {
            pokemon.isPokemonCached(pokes) -> {
                setState { it.setPokemonProfileList(pokes) }
                setPokemonListState(false)
            }
            pokes.isNullOrEmpty() -> {
                setPokemonListState(true)
                setMessageState(resourceProvider.getString(R.string.message_pokemon_not_found))
            }
            else -> {
                setPokemonListState(false)
                setMessageState(resourceProvider.getString(R.string.message_pokemon_not_found))
            }
        }
    }

    private fun setPokemonListState(isEmpty: Boolean) {
        setState { it.setPokemonProfileListState(isEmpty) }
    }
}