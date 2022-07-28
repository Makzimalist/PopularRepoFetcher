package com.github.popular

import com.github.popular.common.repos.GitHubRepository
import com.github.popular.common.usecase.GetAllRepositoriesUseCase
import com.github.popular.ui.main.list.ListViewModel
import com.github.popular.ui.main.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { SearchViewModel() }
    viewModel { params -> ListViewModel( params[0], params[1], get()) }

    single { GitHubRepository(get()) }
    single { GetAllRepositoriesUseCase(get()) }}
