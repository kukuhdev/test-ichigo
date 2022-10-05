package com.eve.testichigo.view.photos.data.repository

import com.eve.testichigo.core.domain.ApiResult
import com.eve.testichigo.view.photos.data.remote.api.PhotoApi
import com.eve.testichigo.view.photos.data.remote.request.PhotosRequest
import com.eve.testichigo.view.photos.data.remote.response.PhotosResponse
import com.eve.testichigo.view.photos.domain.PhotosRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PhotosRepositoryImpl @Inject constructor(private val photoApi: PhotoApi) : PhotosRepository {

    override suspend fun photos(photosRequest: PhotosRequest): Flow<ApiResult<PhotosResponse>> {
        return flow {
            val response = photoApi.photosApi(photosRequest.page, photosRequest.perPage, photosRequest.orderBy)
            if(response.isSuccessful){
                val body = response.body()!!
                emit(ApiResult.Success(body))
            }else{
                val errorResponse = Gson().fromJson(response.message(), PhotosResponse::class.java)
                emit(ApiResult.Error(response.message(), response.code()))
            }
        }
    }
}