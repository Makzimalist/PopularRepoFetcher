package com.github.popular.ui.main.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.popular.common.data.Repository
import com.github.popular.common.usecase.GetAllRepositoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    searchTerm: String,
    getAllRepositoriesUseCase: GetAllRepositoriesUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(value = ViewState.Loading)
    val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val repos = getAllRepositoriesUseCase.searchAllRepositories(searchTerm)
                _viewState.value = ViewState.Data(repos)
            } catch (e: Exception) {
                _viewState.value = ViewState.Error(e.message.orEmpty())
            }
        }
    }

}

sealed class ViewState {
    object Loading : ViewState()
    data class Data(val repositories: List<Repository>) : ViewState() //pass repoData here
    data class Error(val message: String) : ViewState()
}