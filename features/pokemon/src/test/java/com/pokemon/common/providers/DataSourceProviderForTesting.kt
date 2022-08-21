package com.pokemon.common

import com.pokemon.data.api.PokemonProfileService
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

internal fun provideRetrofit(url: String): Retrofit = Retrofit.Builder()
    .baseUrl(url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal fun Retrofit.provideService() = this.create(PokemonProfileService::class.java)

internal fun MockWebServer.enqueueResponse(fileName: String, responseCode: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)
    val source = inputStream?.let { it.source().buffer() }
    source?.let {
        this.enqueue(
            MockResponse()
                .setResponseCode(responseCode)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}
