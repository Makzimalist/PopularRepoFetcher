package com.github.popular

import com.github.popular.ui.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel {
        PopularListingViewModel()
    }

    viewModel { SearchViewModel() }
}
