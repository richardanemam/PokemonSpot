package com.pokemon.presentation.fragments.profilefragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pokemon.R
import com.pokemon.databinding.FragmentPokemonProfileBinding

class PokemonProfileFragment: Fragment(R.layout.fragment_pokemon_profile) {

    private val binding by lazy { FragmentPokemonProfileBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}