package com.example.pokemonspot.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pokemonspot.databinding.ActivityHomeBinding
import com.pokemon.presentation.activity.profileactivity.PokemonProfileActivity

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(binding.root)
        setupClickListener()
    }

    private fun setupClickListener() {
        binding.btnCapturePokemons.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PokemonProfileActivity::class.java))
        }
    }
}