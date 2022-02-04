package com.pokemon.domain.model

data class PokemonProfile(
    val name: String,
    val type: String?,
    val imageUrl: String?,
    val weight: Int?,
    val height: Int?,
    val baseExperience: Int?,
    val abilities: List<String>?,
    val moves: List<String>,
)