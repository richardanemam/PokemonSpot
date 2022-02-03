package com.pokemon.presentation.fragments.profilefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.common.core.arch.UIView
import com.pokemon.R
import com.pokemon.databinding.FragmentPokemonProfileBinding
import com.pokemon.presentation.fragments.profilefragment.viewmodel.PokemonProfileState

class PokemonProfileFragment : Fragment(R.layout.fragment_pokemon_profile),
    UIView<PokemonProfileState> {

    private val binding by lazy { FragmentPokemonProfileBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun render(state: PokemonProfileState) {
        TODO("Not yet implemented")
    }
}