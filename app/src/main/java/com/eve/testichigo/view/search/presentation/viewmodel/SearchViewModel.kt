package com.eve.testichigo.view.search.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eve.testichigo.core.domain.ApiResult
import com.eve.testichigo.view.search.data.remote.request.SearchRequest
import com.eve.testichigo.view.search.data.remote.response.SearchResponse
import com.eve.testichigo.view.search.domain.SearchUseCase
import com.eve.testichigo.view.search.presentation.SearchActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase): ViewModel() {
    val state = MutableStateFlow<SearchActivityState>(SearchActivityState.Init)
    val mState: StateFlow<SearchActivityState> get() = state

    private val _search = MutableStateFlow<SearchResponse?>(null)
    val search : StateFlow<SearchResponse?> get() = _search

    private fun setLoading(){
        state.value = SearchActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = SearchActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = SearchActivityState.ShowToast(message)
    }

    fun setView(type: Int){
        viewModelScope.launch {
            if(type == SearchActivity.VIEW_LIST){
                state.value = SearchActivityState.ViewListGrid(true)
            } else {
                state.value = SearchActivityState.ViewListGrid(false)
            }
        }
    }
    fun search(searchRequest: SearchRequest){
        viewModelScope.launch {
            searchUseCase.search(searchRequest)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { apiResult ->
                    hideLoading()
                    when(apiResult){
                        is ApiResult.Error -> {
                            Log.e("TAG", "search: error")
                            state.value = SearchActivityState.Error(apiResult.message ?: "")
                        }
                        is ApiResult.Success ->{
                            Log.e("TAG", "search: success")
                            state.value = SearchActivityState.Success(apiResult.message ?: "")
                            _search.value = apiResult.data
                        }
                        else -> {
                            Log.e("TAG", "search: else")
                            showToast(apiResult.message ?: "")
                        }
                    }
                }
        }
    }

}

sealed class SearchActivityState  {
    object Init : SearchActivityState()
    data class IsLoading(val isLoading: Boolean) : SearchActivityState()
    data class ShowToast(val message: String) : SearchActivityState()
    data class Success(val msg: String) : SearchActivityState()
    data class Error(val msg: String) : SearchActivityState()
    data class ViewListGrid(val isList: Boolean) : SearchActivityState()
}