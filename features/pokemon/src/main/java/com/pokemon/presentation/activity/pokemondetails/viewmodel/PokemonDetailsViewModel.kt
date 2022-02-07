package com.pokemon.presentation.activity.pokemondetails.viewmodel

import android.content.Intent
import com.common.core.arch.viewmodel.BaseViewModel
import com.common.core.resources.ResourceProvider
import com.pokemon.R
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.presentation.activity.pokemondetails.PokemonDetailsActivity

internal class PokemonDetailsViewModel(private val resourceProvider: ResourceProvider) :
    BaseViewModel<PokemonDetailsState, PokemonDetailsAction>(PokemonDetailsState()) {

    fun sendDisplayInfoAction(intent: Intent?) {
        validateBundle(intent)
    }

    private fun validateBundle(intent: Intent?) {
        if (intent != null && validateExtras(intent)) {
            val pokemon =
                intent.getParcelableExtra<PokemonProfile>(PokemonDetailsActivity.EXTRA_POKEMON_INFO) as PokemonProfile
            sendAction { PokemonDetailsAction.DisplayPokemonInfo(pokemon) }
        } else {
            sendAction { PokemonDetailsAction.DisplayPokemonInfo(null) }
        }
    }

    private fun validateExtras(intent: Intent): Boolean {
        return intent.hasExtra(PokemonDetailsActivity.EXTRA_POKEMON_INFO)
    }

    fun updateState(pokemonProfile: PokemonProfile?) {
        if (pokemonProfile != null) {
            setState {
                it
                    .setInfoAvailability(pokemonProfile)
                    .setErrorMessage(null)
            }
        } else {
            setState { it.setErrorMessage(resourceProvider.getString(R.string.pokemon_details_error_message)) }
        }
    }
}