package com.pokemon.presentation.activity.pokemondetails

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import com.common.core.arch.UIView
import com.common.extensions.loadImage
import com.common.extensions.toCustomString
import com.pokemon.R
import com.pokemon.databinding.ActivityPokemonDetailsBinding
import com.pokemon.domain.model.PokemonProfile
import com.pokemon.presentation.activity.pokemondetails.viewmodel.PokemonDetailsAction
import com.pokemon.presentation.activity.pokemondetails.viewmodel.PokemonDetailsState
import com.pokemon.presentation.activity.pokemondetails.viewmodel.PokemonDetailsViewModel
import com.pokemon.presentation.extensions.addCustomItemView
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailsActivity : AppCompatActivity(), UIView<PokemonDetailsState> {

    private val binding by lazy { ActivityPokemonDetailsBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<PokemonDetailsViewModel>()

    companion object {
        const val EXTRA_POKEMON_INFO = "EXTRA_POKEMON_INFO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)

        if (savedInstanceState == null) {
            viewModel.sendDisplayInfoAction(intent)
        }

        setupToolbar()
        subscribeObservers()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarPokemonDetails)
        supportActionBar?.apply {
            setTitle(R.string.pokemon_toolbar_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeObservers() {
        subscribeAction()
        subscribeState()
    }

    private fun subscribeAction() {
        viewModel.action.observe(this, {
            when (it) {
                is PokemonDetailsAction.DisplayPokemonInfo -> viewModel.updateState(it.pokemon)
            }
        })
    }

    private fun subscribeState() {
        viewModel.state.observe(this, {
            viewModel.state.observe(this, {
                render(it)
            })
        })
    }

    override fun render(state: PokemonDetailsState) {
        with(state) {
            pokemonInfo?.let { setupPokemonInfo(it) }
            errorMessage?.let { setInfoNotAvailable(it) }
        }
    }

    private fun setupPokemonInfo(pokemonInfo: PokemonProfile) {
        with(pokemonInfo) {

            imageUrl?.let { binding.ivPokemonDetails.loadImage(it) }

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_name_title),
                name
            )

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_type_title),
                type
            )

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_weight_title),
                weight.toString()
            )

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_height_title),
                height.toString()
            )

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_base_experience_title),
                baseExperience.toString()
            )

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_abilities_title),
                abilities?.toCustomString()
            )

            binding.llPokemonDetails.addCustomItemView(
                getString(R.string.pokemon_details_moves_title),
                moves.toCustomString()
            )
        }
    }

    private fun setInfoNotAvailable(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
        finish()
    }
}