package com.github.popular.ui.main.search

import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp() {
        searchViewModel = SearchViewModel()
    }

    @Test
    fun `If we update the searchTerm, we want to notify the UI via an updated state`() {
        searchViewModel.search("Hello World")
        Truth.assertThat(searchViewModel.searchString.value).isEqualTo("Hello World")
    }

    @Test
    fun `If we update the sorting, we want to notify the UI via an updated state`() {
        searchViewModel.updateSorting(searchViewModel.sortingOptions.last())
        Truth.assertThat(searchViewModel.sorting.value).isEqualTo("updated")
    }
}