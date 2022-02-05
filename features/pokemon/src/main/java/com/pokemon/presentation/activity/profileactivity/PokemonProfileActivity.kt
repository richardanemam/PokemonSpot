package com.pokemon.presentation.activity.profileactivity

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.common.core.arch.UIView
import com.pokemon.R
import com.pokemon.databinding.ActivityPokemonProfileBinding
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.presentation.activity.profileactivity.viewmodel.PokemonProfileState
import com.pokemon.presentation.activity.profileactivity.viewmodel.PokemonProfileViewModel
import com.pokemon.presentation.adapter.PokemonProfileAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonProfileActivity : AppCompatActivity(), UIView<PokemonProfileState> {

    private val binding by lazy { ActivityPokemonProfileBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<PokemonProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        subscribeStateObserver()
        setupViews()

    }

    private fun subscribeStateObserver() = viewModel.state.observe(this, { render(it) })

    private fun setupViews() {
        setupToolbar()
        setupSearchView()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarPokemonProfile)
        supportActionBar?.apply {
            setTitle(R.string.pokemon_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupSearchView() {
        binding.svPokemonProfile.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { pokemon -> viewModel.fetchPokemon(pokemon) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //Do nothing
                return true
            }

        })
    }

    override fun render(state: PokemonProfileState) {
        with(state) {
            binding.pbPokemonProfile.isVisible = isLoading
            binding.tvSearchScreenSearchForNewUsers.isVisible = isFirstSearch
            setupRecyclerView(state.pokemonProfileList)
            message?.let { renderMessage(it) }
        }
    }

    private fun setupRecyclerView(pokemons: List<PokemonProfile>) {
        binding.rvPokemonProfile.adapter = PokemonProfileAdapter(pokemons) {
            //Start new activity
            Toast.makeText(this, "Funcionando...", Toast.LENGTH_LONG).show()
        }

        LinearLayoutManager(this).apply {
            binding.rvPokemonProfile.layoutManager = this
        }
    }

    private fun renderMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}