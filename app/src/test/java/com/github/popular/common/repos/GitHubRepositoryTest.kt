package com.github.popular.common.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.popular.common.data.Repository
import com.github.popular.network.PopularRepositoriesResponse
import com.github.popular.network.RepositoriesApi
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class GitHubRepositoryTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val apiMockk = mockk<RepositoriesApi>()

    private lateinit var gitHubRepository: GitHubRepository

    private val dispatcher = StandardTestDispatcher()

    // these dummy data could go into a separate file for testing objects to not duplicate them
    private val repository = Repository(
        id = "id-1",
        name = "first",
        starsCount = 3,
        forksCount = 4,
        openIssuesCount = 5,
        owner = Repository.RepositoryOwner("Maks", "someURL.org"),
        updatedAt = Date(),
        description = null,
        language = null
    )

    private val apiRepository = PopularRepositoriesResponse.ApiRepository(
        id = "id-1",
        name = "first",
        starsCount = 3,
        forksCount = 4,
        openIssuesCount = 5,
        owner = PopularRepositoriesResponse.ApiRepository.ApiRepositoryOwner("Maks", "someURL.org"),
        updatedAt = Date(),
        description = null,
        language = null
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        gitHubRepository = GitHubRepository(apiMockk)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `If we trigger a search call, we want to receive a domain mapped List`() {
        runTest {
            coEvery {
                apiMockk.getPopularRepos(
                    "keyword",
                    1,
                    100,
                    "stars"
                )
            } returns PopularRepositoriesResponse(listOf(apiRepository))

            val repos = gitHubRepository.searchAllRepositories("keyword")

            testScheduler.advanceUntilIdle()

            coVerify(exactly = 1) { apiMockk.getPopularRepos(any(), any(), any(), any()) }

            Truth.assertThat(repos.size).isEqualTo(1)
            Truth.assertThat(repos.first()).isInstanceOf(Repository::class.java)
            Truth.assertThat(repos.first().id).isEqualTo(repository.id)
        }
    }
}