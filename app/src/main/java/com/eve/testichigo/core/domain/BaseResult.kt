package com.eve.testichigo.core.domain

sealed class BaseResult <out T : Any> {
    class OnLoading(val message: String? = null) : BaseResult<Nothing>()
    class OnSuccess<out T: Any>(val data : T? = null, val message: String? = null) : BaseResult<T>()
    class OnError(val status: String? = null, val message : String? = null) : BaseResult<Nothing>()
}