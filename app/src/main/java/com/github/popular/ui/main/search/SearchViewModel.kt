package com.github.popular.ui.main.search

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class SearchViewModel: ViewModel() {

    private val _searchString = MutableStateFlow("")
    val searchString = _searchString.asStateFlow()

    fun search(search: String) {
        _searchString.value = search
    }
}