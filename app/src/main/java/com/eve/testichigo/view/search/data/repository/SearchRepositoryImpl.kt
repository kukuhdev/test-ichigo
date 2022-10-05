package com.eve.testichigo.view.search.data.repository

import com.eve.testichigo.core.domain.ApiResult
import com.eve.testichigo.view.search.data.remote.api.SearchApi
import com.eve.testichigo.view.search.data.remote.request.SearchRequest
import com.eve.testichigo.view.search.data.remote.response.SearchResponse
import com.eve.testichigo.view.search.domain.SearchRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SearchRepositoryImpl @Inject constructor(private val searchApi: SearchApi) : SearchRepository {

    override suspend fun search(searchRequest: SearchRequest): Flow<ApiResult<SearchResponse>> {
        return flow {
            val response = searchApi.photosApi(searchRequest.page, searchRequest.perPage, searchRequest.query)
            if(response.isSuccessful){
                val body = response.body()!!
                emit(ApiResult.Success(body))
            }else{
                val errorResponse = Gson().fromJson(response.message(), SearchResponse::class.java)
                emit(ApiResult.Error(response.message(), response.code()))
            }
        }
    }
}