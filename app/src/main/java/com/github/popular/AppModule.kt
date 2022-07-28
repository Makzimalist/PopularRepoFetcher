package com.github.popular

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel {
        PopularListingViewModel()
    }

}
