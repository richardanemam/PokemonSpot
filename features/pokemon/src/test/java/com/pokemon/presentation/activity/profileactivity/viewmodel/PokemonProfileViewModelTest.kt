package com.pokemon.presentation.activity.profileactivity.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.common.core.resources.ResourceProvider
import com.common.core.resources.ResourceProviderImpl
import com.pokemon.common.getPokemonProfile
import com.pokemon.common.getPokemonProfileList
import com.pokemon.domain.usecase.PokemonProfileUseCase
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
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
    private val resourceProvider: ResourceProvider = mockk<ResourceProviderImpl>()

    private fun createViewModel() = PokemonProfileViewModel(
        useCase,
        resourceProvider
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

    @Test
    fun `init should display initial state`() {
        //given
        coEvery { useCase.getAllPokemons() } returns emptyList()

        //when
        val viewModel = createViewModel()

        //then
        Assert.assertEquals(
            PokemonProfileState(
                pokemonProfileList = emptyList(),
                isLoading = false,
                message = null
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `fetchPokemon should validate if the pokemon is not cached and then update the state`() {
        //given
        coEvery { useCase.getAllPokemons() } returns emptyList() andThen emptyList() andThen getPokemonProfileList()
        coEvery { useCase.fetchPokemonProfile(any()) } just Runs

        //when
        val viewModel = createViewModel()
        viewModel.fetchPokemon("charizard")

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

    @Test
    fun `fetchPokemon should warn the user that the pokemon is already cached`() {
        //given
        coEvery { resourceProvider.getString(any()) } returns "Pokemon listado nas pesquisas recentes"
        coEvery { useCase.getAllPokemons() } returns getPokemonProfileList() andThen getPokemonProfileList()
        coEvery { useCase.fetchPokemonProfile(any()) } just Runs

        //when
        val viewModel = createViewModel()
        viewModel.fetchPokemon("charizard")

        //then
        Assert.assertEquals(
            PokemonProfileState(
                pokemonProfileList = getPokemonProfileList(),
                isLoading = false,
                message = "Pokemon listado nas pesquisas recentes"
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `searchPokemon should set error message when pokemon is null`() {
        //given
        coEvery { useCase.getAllPokemons() } returns emptyList()
        coEvery { resourceProvider.getString(any()) } returns "Acesso à internet indisponível"

        //when
        val viewModel = createViewModel()
        viewModel.searchPokemon(null)

        //then
        Assert.assertEquals(
            PokemonProfileState(
                pokemonProfileList = emptyList(),
                isLoading = false,
                message = "Acesso à internet indisponível"
            ),
            viewModel.state.value
        )
    }

    @Test
    fun `searchPokemon should send an action to fetch pokemon`() {
        //given
        coEvery { useCase.getAllPokemons() } returns emptyList()

        //when
        val viewModel = createViewModel()
        viewModel.searchPokemon("charizard")

        Assert.assertEquals(
            PokemonProfileAction.FetchPokemon("charizard"),
            viewModel.action.value
        )
    }

    @Test
    fun `navigateToDetails should send an action to open details screen`() {
        //given
        coEvery { useCase.getAllPokemons() } returns emptyList()

        //when
        val viewModel = createViewModel()
        viewModel.navigateToDetails(getPokemonProfile())

        Assert.assertEquals(
            PokemonProfileAction.NavigateToDetails(getPokemonProfile()),
            viewModel.action.value
        )
    }
}