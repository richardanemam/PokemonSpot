package com.pokemon.data.api

import com.pokemon.data.model.PokemonProfileResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonProfileService {

    @GET("{pokemon}")
    suspend fun getPokemon(@Path("pokemon") pokemon: String): Response<PokemonProfileResponse>
}