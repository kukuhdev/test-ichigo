package com.eve.testichigo.view.photos.domain

import com.eve.testichigo.core.domain.ApiResult
import com.eve.testichigo.view.photos.data.remote.request.PhotosRequest
import com.eve.testichigo.view.photos.data.remote.response.PhotosResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotosUseCase @Inject constructor(private val photosRepository: PhotosRepository) {
    suspend fun photos(photosRequest: PhotosRequest): Flow<ApiResult<PhotosResponse>> {
        return photosRepository.photos(photosRequest)
    }
}