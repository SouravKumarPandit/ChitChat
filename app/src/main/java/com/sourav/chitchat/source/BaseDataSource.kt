package com.sourav.chitchat.source

import com.google.gson.Gson
import retrofit2.Response
import kotlin.contracts.InvocationKind


/**
 * Abstract Base Data source class with error handling
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseDataSource {
    protected suspend fun <T : ResponseDTO<*>> getResult(call: suspend () -> Response<T>): T {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return body
            } else if (response.body() is ResponseDTO<*>) {
                return ResponseDTO.error(
                    title = response.body()?.title,
                    message = response.body()?.message,
                    data = response.body()?.data,
                    code = response.body()?.code,
                    displayType = response.body()?.displayType
                ) as T

            }
            val errorBody = response.errorBody()
            if (errorBody != null) {
                val responseDTO = Gson().fromJson(errorBody.string(), ResponseDTO::class.java)
                return ResponseDTO.error(
                    title = responseDTO.title,
                    message = responseDTO.message,
                    data = responseDTO.data,
                    code = responseDTO.code,
                    displayType = responseDTO.displayType
                ) as T
            }
            /*Your app should never come at this point*/
            return ResponseDTO.error(
                title = "Unknown Error",
                message = "Sorry, Something unexpected happened",
                data = null,
                code = -1,
                displayType = ""
            ) as T
        } catch (e: java.net.UnknownHostException) {
            return ResponseDTO.error(
                title = "No Internet Connection",
                message = "You are not connected to internet.\nMake sure wi-fi OR mobile data is on,Airplane Mode is off and try again",
                data = null,
                code = -1,
                displayType = ""
            ) as T
        } catch (e: java.net.ConnectException) {
            return ResponseDTO.error(
                title = "No Internet Connection",
                message = "You are not connected to internet.\nMake sure wi-fi OR mobile data is on,Airplane Mode is off and try again",
                data = null,
                code = -1,
                displayType = ""
            ) as T
        } catch (e: Exception) {
            /*Try to  never Reach this point*/
            return ResponseDTO.error(
                type = -1,
                title = "Oops !! something really bad happened ",
                message = e.message,
                data = null,
                code = 0,
                displayType = ""
            ) as T
        }
    }
}

