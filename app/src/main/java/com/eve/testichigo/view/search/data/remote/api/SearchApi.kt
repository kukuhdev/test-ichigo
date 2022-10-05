package com.eve.testichigo.view.search.data.remote.api

import com.eve.testichigo.view.search.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("search/photos")
    suspend fun photosApi(
        @Query("page") page: String?,
        @Query("per_page") perPage: String?,
        @Query("query") query: String?
    ) : Response<SearchResponse>
}