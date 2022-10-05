package com.eve.testichigo.view.search.domain

import com.eve.testichigo.core.domain.ApiResult
import com.eve.testichigo.view.search.data.remote.request.SearchRequest
import com.eve.testichigo.view.search.data.remote.response.SearchResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {
    suspend fun search(searchRequest: SearchRequest): Flow<ApiResult<SearchResponse>> {
        return searchRepository.search(searchRequest)
    }
}