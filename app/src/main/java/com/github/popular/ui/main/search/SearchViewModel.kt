package com.github.popular.ui.main.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SearchViewModel: ViewModel() {

    val sortingOptions = listOf("stars", "forks", "updated")

    private val _searchString = MutableStateFlow("")
    val searchString = _searchString.asStateFlow()

    private val _sorting = MutableStateFlow(sortingOptions.first())
    val sorting = _sorting.asStateFlow()

    fun search(search: String) {
        _searchString.value = search
    }

    fun updateSorting(sort: String) {
        _sorting.value = sort
    }
}