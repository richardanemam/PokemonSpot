package com.pokemon.presentation.fragments.profilefragment.viewmodel

import com.common.core.arch.UIAction
import com.pokemon.domain.model.PokemonProfile

sealed class PokemonProfileAction: UIAction {
    data class NavigateToDetails(val profileDetails: PokemonProfile): PokemonProfileAction()
}