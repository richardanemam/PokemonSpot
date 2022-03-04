package com.pokemon.presentation.activity.profileactivity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pokemon.common.getPokemonProfileList
import com.pokemon.domain.usecase.PokemonProfileUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

internal class PokemonProfileViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcher()

    private val useCase: PokemonProfileUseCase = mockk()

    private fun createViewModel() = PokemonProfileViewModel(
        useCase,
        mockk()
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should display cached data`() {
        //given
        coEvery { useCase.getAllPokemons() } returns getPokemonProfileList()

        //when
        val viewModel = createViewModel()

        //then
        Assert.assertEquals(
            PokemonProfileState(
                pokemonProfileList = getPokemonProfileList(),
                isLoading = false,
                message = null
            ),
            viewModel.state.value
        )
    }
}