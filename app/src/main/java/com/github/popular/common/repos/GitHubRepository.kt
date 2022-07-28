package com.github.popular.common.repos

import com.github.popular.common.data.Repository
import com.github.popular.network.PopularRepositoriesResponse
import com.github.popular.network.RepositoriesApi

class GitHubRepository(
    private val repositoriesApi: RepositoriesApi
) {

    // in a perfect world we maybe would cache them in a database before deliver them to the UI layer
    suspend fun searchAllRepositories(search: String, sort: String = "stars"): List<Repository> {
        return repositoriesApi.getPopularRepos(
            keyWord = search,
            page = 1,
            pageSize = 100,
            sort = sort
        ).items.map { it.toDomain() }
    }
}

private fun PopularRepositoriesResponse.ApiRepository.toDomain(): Repository {
    return Repository(
        id = this.id,
        name = this.name,
        description = this.description,
        language = this.language,
        starsCount = this.starsCount,
        forksCount = this.forksCount,
        openIssuesCount = this.openIssuesCount,
        owner = this.owner.toDomain(),
        updatedAt = updatedAt
    )
}

private fun PopularRepositoriesResponse.ApiRepository.ApiRepositoryOwner.toDomain(): Repository.RepositoryOwner {
    return Repository.RepositoryOwner(loginName, avatarUrl)
}
