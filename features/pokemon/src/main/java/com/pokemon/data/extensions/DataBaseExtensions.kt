package com.pokemon.data.extensions

import com.pokemon.data.localdatasource.PokemonProfileEntity
import com.pokemon.domain.model.PokemonProfile

fun PokemonProfile.toEntity(): PokemonProfileEntity {
    return with(this) {
        PokemonProfileEntity(
            name = name,
            type = type,
            imageUrl = imageUrl,
            weight = weight,
            height = height,
            baseExperience = baseExperience,
            abilities = abilities,
            moves = moves
        )
    }
}

fun PokemonProfileEntity.toModel(): PokemonProfile {
    return with(this) {
        PokemonProfile(
            name = name,
            type = type,
            imageUrl = imageUrl,
            weight = weight,
            height = height,
            baseExperience = baseExperience,
            abilities = abilities,
            moves = moves
        )
    }
}