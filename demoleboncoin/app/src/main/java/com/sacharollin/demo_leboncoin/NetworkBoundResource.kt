package com.sacharollin.demo_leboncoin

import kotlinx.coroutines.flow.*

enum class NBRStatus {
    ERROR,
    LOADING,
    SUCCESS
}

data class Resource<out T>(val status: NBRStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> Success(data: T?): Resource<T> {
            return Resource(NBRStatus.SUCCESS, data, null);
        }

        fun <T> Loading(data: T?): Resource<T> {
            return Resource(NBRStatus.LOADING, data, null);
        }

        fun <T> Error(msg: String, data: T?): Resource<T> {
            return Resource(NBRStatus.ERROR, data, msg);
        }
    }
}

/**
 * Provide resource both from the API server and the local DB
 *
 */
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow<Resource<ResultType>> {
    emit(Resource.Loading(null))
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            onFetchFailed(throwable)
            query().map { Resource.Error(throwable.message ?: "Une erreur est survenue", it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}