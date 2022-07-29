package com.github.popular.ui.main.list

import com.github.popular.common.data.Repository
import com.github.popular.common.usecase.GetAllRepositoriesUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest {

    private val allRepositoriesUseCaseMockk = mockk<GetAllRepositoriesUseCase>()

    private lateinit var viewModel: ListViewModel

    private val owner = Repository.RepositoryOwner("Maks", "someURL.org")

    // these dummy data could go into a separate file for testing objects to not duplicate them
    private val repoList = listOf(
        Repository(
            id = "id-1",
            name = "first",
            starsCount = 3,
            forksCount = 4,
            openIssuesCount = 5,
            owner = owner,
            updatedAt = Date(),
            description = null,
            language = null
        ),
        Repository(
            id = "id-2",
            name = "Second",
            starsCount = 3,
            forksCount = 4,
            openIssuesCount = 5,
            owner = owner.copy(loginName = "user1256"),
            updatedAt = Date(),
            description = null,
            language = null
        ),
        Repository(
            id = "id-3",
            name = "tres",
            starsCount = 3,
            forksCount = 4,
            openIssuesCount = 5,
            owner = owner,
            updatedAt = Date(),
            description = null,
            language = null
        ),
    )
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `If we enter the screen we want to see repositories matching our search criteria`() {
        runTest {
            coEvery {
                allRepositoriesUseCaseMockk.searchAllRepositories("hello", "stars")
            } returns repoList

            viewModel = ListViewModel("hello", "stars", allRepositoriesUseCaseMockk)
            Truth.assertThat(viewModel.viewState.value).isInstanceOf(ViewState.Loading::class.java)

            testScheduler.advanceUntilIdle()

            Truth.assertThat((viewModel.viewState.value as ViewState.Data).repositories).hasSize(3)
            Truth.assertThat((viewModel.viewState.value as ViewState.Data).repositories)
                .isEqualTo(repoList)
        }
    }

    @Test
    fun `If we enter the screen, and an error occures we want to see a Error Screen`() {
        runTest {
            coEvery {
                allRepositoriesUseCaseMockk.searchAllRepositories(any(), any())
            } throws HttpException(Response.error<Repository>(422, "Some message".toResponseBody()))

            viewModel = ListViewModel(null, null, allRepositoriesUseCaseMockk)
            Truth.assertThat(viewModel.viewState.value).isInstanceOf(ViewState.Loading::class.java)

            testScheduler.advanceUntilIdle()

            Truth.assertThat(viewModel.viewState.value).isInstanceOf(ViewState.Error::class.java)
        }
    }

    @Test
    fun `If we enter the screen, we want to see the search term and sorting as title`() {
        runTest {
            coEvery {
                allRepositoriesUseCaseMockk.searchAllRepositories("hello", "stars")
            } returns emptyList()

            viewModel = ListViewModel("hello", "stars", allRepositoriesUseCaseMockk)

            Truth.assertThat(viewModel.title.value).isEqualTo("hello | stars")
        }
    }
}