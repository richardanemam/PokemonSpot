package com.pokemon.presentation.activity.profileactivity

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.pokemon.databinding.ActivityPokemonProfileBinding
import com.pokemon.presentation.activity.profileactivity.viewmodel.PokemonProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonProfileActivity: AppCompatActivity() {

    private val binding by lazy { ActivityPokemonProfileBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<PokemonProfileViewModel>()

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding.svPokemonProfile.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {pokemon -> viewModel.fetchPokemon(pokemon)}
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Do nothing
                return true
            }

        })
    }
}