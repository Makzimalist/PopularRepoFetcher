package com.github.popular.common.data

import java.util.*


data class Repository(
    val id: String,
    val name: String,
    val description: String?,
    val language: String?,
    val starsCount: Int,
    val forksCount: Int,
    val openIssuesCount: Int,
    val owner: RepositoryOwner,
    val updatedAt: Date
) {
    data class RepositoryOwner(
        val loginName: String,
        val avatarUrl: String
    )
}