package com.sourav.chitchat.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [APIConstant.Status.SUCCESS] - with data from database
 * [APIConstant.Status.ERROR] - if error has occurred from any source
 * [APIConstant.Status.LOADING]- showing progress indicator for network request
 */
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> ResponseDTO<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<ResponseDTO<T>> =
    liveData(Dispatchers.IO) {
        emit(ResponseDTO.loading<T>())
        val source = databaseQuery.invoke().map {
            ResponseDTO.success<T>(
                null,
                null,
                APIConstant.Status.SUCCESS,
                it,
                0,
                null
            )
        }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.type == APIConstant.Status.SUCCESS) {
            if (responseStatus.data != null) {
                saveCallResult(responseStatus.data)
            }
            emitSource(source)
        } else if (responseStatus.type == APIConstant.Status.ERROR) {
            emit(ResponseDTO.error<T>(null, null, APIConstant.Status.ERROR, null, 0, null))
            emitSource(source)
        }
    }


fun <T> resultStrictTypeLiveData(
    networkCall: suspend () -> ResponseDTO<T>,
    saveCallResult: suspend (T) -> Unit,
    databaseQuery: () -> LiveData<T>
): LiveData<ResponseDTO<T>> =
    liveData(Dispatchers.IO) {

        emit(ResponseDTO.loading<T>())
        val respObj = networkCall.invoke()
        when (respObj.type) {
            APIConstant.Status.SUCCESS -> {
                emit(
                    ResponseDTO.success<T>(
                        respObj.title,
                        respObj.message,
                        respObj.type,
                        respObj.data,
                        respObj.code,
                        respObj.displayType
                    )
                )
                saveCallResult(respObj.data!!)
            }
            APIConstant.Status.ERROR -> {
                val source = databaseQuery.invoke().map {
                    ResponseDTO.error<T>(
                        respObj.title,
                        respObj.message,
                        respObj.type,
                        it,
                        respObj.code,
                        respObj.displayType
                    )
                }

                emitSource(source)


            }
            else -> {
                emit(
                    ResponseDTO.error<T>(
                        respObj.title,
                        respObj.message,
                        respObj.type,
                        null,
                        respObj.code,
                        respObj.displayType
                    )
                )
            }
        }
    }

fun <T> databaseLiveData(databaseQuery: () -> LiveData<T>): LiveData<T> =
    liveData(Dispatchers.IO) {
        try {
            val source = databaseQuery.invoke().map {
                it
            }
            emitSource(source)
        } catch (exception: Exception) {
        }

    }
/*fun <A> responseObject(networkCall: suspend () ->A?): LiveData<A?> =
    liveData(Dispatchers.IO) {
        emit(null)
        val respObj = networkCall.invoke()
        emit(respObj)

    }*/

suspend fun <A> networkData(networkCall: suspend () -> ResponseDTO<A>): ResponseDTO<A> =
    networkCall.invoke()

fun <A> networkLiveData(networkCall: suspend () -> ResponseDTO<A>): LiveData<ResponseDTO<A>> =
    liveData(Dispatchers.IO) {
        emit(ResponseDTO.loading())
        val respObj = networkCall.invoke()
        when (respObj.type) {
            APIConstant.Status.SUCCESS, APIConstant.Status.ERROR -> {
                    emit(
                        ResponseDTO<A>(
                            respObj.title,
                            respObj.message,
                            respObj.type,
                            respObj.data,
                            respObj.code,
                            respObj.displayType
                        )
                    )
            }
            else -> {
                emit(
                    ResponseDTO.error<A>(
                        "Server Broken",
                        "Some unknown error from server,Invalid Response",
                        APIConstant.Status.ERROR,
                        null,
                        0,
                        null
                    )
                )

            }
        }
    }


fun <B> backgroundLiveData(backgroundCallback: suspend () -> ResponseDTO<B>): LiveData<ResponseDTO<B>> =
    liveData(Dispatchers.IO) {
        emit(ResponseDTO.loading())
        try {
            emit(backgroundCallback())
        } catch (e: Exception) {
            emit(ResponseDTO.error<B>(null, null, APIConstant.Status.ERROR, null, null, null))

        }
    }

fun updateDatabase(
    databaseQuery: suspend () -> Unit
): Unit = runBlocking { databaseQuery.invoke() }

fun <T> clearCartLiveData(
    networkCall: suspend () -> ResponseDTO<T>,
    updateNetworkCall: suspend () -> ResponseDTO<T>,
    saveCallResult: suspend (T) -> Unit,
    backgroundCallback: suspend () -> Unit
): LiveData<ResponseDTO<T>> =
    liveData(Dispatchers.IO) {

        emit(ResponseDTO.loading())

        val networkCallObj = networkCall.invoke()
        when (networkCallObj.type) {

            APIConstant.Status.SUCCESS -> {

                val updateNetCallObj = updateNetworkCall.invoke()

                when (updateNetCallObj.type) {
                    APIConstant.Status.SUCCESS -> {
                        saveCallResult(updateNetCallObj.data!!)
                        backgroundCallback()
                        emit(
                            ResponseDTO.success<T>(
                                updateNetCallObj.title,
                                updateNetCallObj.message,
                                updateNetCallObj.type,
                                updateNetCallObj.data,
                                updateNetCallObj.code,
                                updateNetCallObj.displayType
                            )
                        )
                    }

                    APIConstant.Status.ERROR -> {
                        emit(
                            ResponseDTO.error<T>(
                                updateNetCallObj.title,
                                updateNetCallObj.message,
                                updateNetCallObj.type,
                                updateNetCallObj.data,
                                updateNetCallObj.code,
                                updateNetCallObj.displayType
                            )
                        )
                    }
                }

            }
            APIConstant.Status.ERROR -> {
                emit(
                    ResponseDTO.error<T>(
                        networkCallObj.title,
                        networkCallObj.message,
                        networkCallObj.type,
                        networkCallObj.data,
                        networkCallObj.code,
                        networkCallObj.displayType
                    )
                )
            }
            else -> {
                emit(ResponseDTO.error<T>(null, null, APIConstant.Status.ERROR, null, 0, null))

            }
        }

    }

fun <A> singleNetoworkEmit(
    networkCall: suspend () -> ResponseDTO<A>,
    handleResponse: suspend (A, Int) -> Unit
): LiveData<ResponseDTO<A>> =
    liveData(Dispatchers.IO) {
        emit(ResponseDTO.loading<A>())

        val responseStatus = networkCall.invoke()
        responseStatus.data?.let {
            handleResponse(
                it,
                responseStatus.type ?: APIConstant.Status.ERROR
            )
        }

    }
