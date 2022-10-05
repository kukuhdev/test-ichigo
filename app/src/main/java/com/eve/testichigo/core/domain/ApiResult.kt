package com.eve.testichigo.core.domain

sealed class ApiResult<T>(
    val data: T? = null,
    val message: String? = null,
    val status: Int? = 0,
    val err: String? = null
) {
    class Success<T>(data: T? = null) : ApiResult<T>(data)
    class NotSuccess<T>(err: String?, data: T? = null,) : ApiResult<T>(data, err)
    class Error<T>(message: String?, status: Int?, data: T? = null) : ApiResult<T>(data, message, status)
}