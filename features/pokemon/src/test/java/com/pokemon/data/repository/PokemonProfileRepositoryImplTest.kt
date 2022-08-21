package com.pokemon.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pokemon.common.enqueueResponse
import com.pokemon.common.provideRetrofit
import com.pokemon.common.provideService
import com.pokemon.data.mapper.PokemonProfileResponseMapper
import com.pokemon.domain.repository.PokemonProfileCachePolicyRepository
import com.pokemon.domain.repository.PokemonProfileRepository
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


internal class PokemonProfileRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    private val mockWebServer = MockWebServer()
    private val url = mockWebServer.url("").toString()
    private val retrofit = provideRetrofit(url)
    private val service = retrofit.provideService()
    private val cache: PokemonProfileCachePolicyRepository =
        mockk<PokemonProfileCachePolicyRepositoryImpl>()
    private val mapper: PokemonProfileResponseMapper = mockk()


    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        mockWebServer.start()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mockWebServer.shutdown()
    }


    private fun createRepository(): PokemonProfileRepository {
        return PokemonProfileRepositoryImpl(service, cache, mapper)
    }

    @Test
    fun `fetchPokemons should receive a pokemon name as a paremeter make a request and cache it`() = runBlocking {
        //Given
        mockWebServer.enqueueResponse("pokemon_200.json", 200)

        //When
        val repository = createRepository()
        repository.fetchPokemons("ditto")

        //then
        verify(exactly = 1) {
           
        }
    }


    fun testGetAllPokemons() {}
}