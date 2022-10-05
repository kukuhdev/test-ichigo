package com.eve.testichigo.view.photos.data.remote.request

import com.google.gson.annotations.SerializedName

data class PhotosRequest(
    @SerializedName("page") val page: String?,
    @SerializedName("per_page") val perPage: String?,
    @SerializedName("order_by") val orderBy: String?
)
