package com.test.coinapi.domain

sealed class UseCaseResult<out T> {
    data class Success<out T>(val data: T) : UseCaseResult<T>()
    data class Failure(val error: Throwable) : UseCaseResult<Unit>()

    fun then(
        onSuccess: ((T) -> Unit)? = null,
        onError: ((Throwable) -> Unit)? = null
    ): UseCaseResult<T> {
        when (this) {
            is Success -> onSuccess?.invoke(this.data)
            is Failure -> onError?.invoke(this.error)
        }
        return this
    }
}

