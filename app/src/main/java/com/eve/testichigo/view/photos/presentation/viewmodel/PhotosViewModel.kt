package com.eve.testichigo.view.photos.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eve.testichigo.core.domain.ApiResult
import com.eve.testichigo.view.photos.data.remote.request.PhotosRequest
import com.eve.testichigo.view.photos.data.remote.response.PhotosResponse
import com.eve.testichigo.view.photos.domain.PhotosUseCase
import com.eve.testichigo.view.photos.presentation.PhotoActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photosUseCase: PhotosUseCase): ViewModel() {
    val state = MutableStateFlow<PhotosActivityState>(PhotosActivityState.Init)
    val mState: StateFlow<PhotosActivityState> get() = state

    private val _photos = MutableStateFlow<PhotosResponse?>(null)
    val photos : StateFlow<PhotosResponse?> get() = _photos

    private fun setLoading(){
        state.value = PhotosActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = PhotosActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = PhotosActivityState.ShowToast(message)
    }

    fun setView(type: Int){
        viewModelScope.launch {
            if(type == PhotoActivity.VIEW_LIST){
                state.value = PhotosActivityState.ViewListGrid(true)
            } else {
                state.value = PhotosActivityState.ViewListGrid(false)
            }
        }
    }
    fun photos(photosRequest: PhotosRequest){
        viewModelScope.launch {
            photosUseCase.photos(photosRequest)
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
                            Log.e("TAG", "photos: error")
                            state.value = PhotosActivityState.Error(apiResult.message ?: "")
                        }
                        is ApiResult.Success ->{
                            Log.e("TAG", "photos: success")
                            state.value = PhotosActivityState.Success(apiResult.message ?: "")
                            _photos.value = apiResult.data
                        }
                        else -> {
                            Log.e("TAG", "photos: else")
                            showToast(apiResult.message ?: "")
                        }
                    }
                }
        }
    }

}

sealed class PhotosActivityState  {
    object Init : PhotosActivityState()
    data class IsLoading(val isLoading: Boolean) : PhotosActivityState()
    data class ShowToast(val message: String) : PhotosActivityState()
    data class Success(val msg: String) : PhotosActivityState()
    data class Error(val msg: String) : PhotosActivityState()
    data class ViewListGrid(val isList: Boolean) : PhotosActivityState()
}