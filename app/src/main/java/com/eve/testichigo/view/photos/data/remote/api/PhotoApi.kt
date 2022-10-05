package com.eve.testichigo.view.photos.data.remote.api

import com.eve.testichigo.view.photos.data.remote.response.PhotosResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PhotoApi {
    @GET("photos")
    suspend fun photosApi(
        @Query("page") page: String?,
        @Query("per_page") perPage: String?,
        @Query("order_by") orderBy: String?
    ) : Response<PhotosResponse>
}