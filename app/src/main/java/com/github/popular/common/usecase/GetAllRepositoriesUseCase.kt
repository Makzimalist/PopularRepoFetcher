package com.github.popular.common.usecase

import com.github.popular.common.data.Repository
import com.github.popular.common.repos.GitHubRepository

class GetAllRepositoriesUseCase(
    private val gitHubRepository: GitHubRepository
) {

    suspend fun searchAllRepositories(search: String, sort: String): List<Repository> {
        return gitHubRepository.searchAllRepositories(search, sort)
    }
}