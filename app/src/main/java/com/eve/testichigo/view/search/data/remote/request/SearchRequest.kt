package com.eve.testichigo.view.search.data.remote.request

import com.google.gson.annotations.SerializedName

data class SearchRequest(
    @SerializedName("page") val page: String?,
    @SerializedName("per_page") val perPage: String?,
    @SerializedName("query") val query: String?
)
