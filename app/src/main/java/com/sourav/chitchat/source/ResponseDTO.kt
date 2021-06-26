/*
 *   Created by Sourav Kumar Pandit  24 - 4 - 2020
 */
package com.sourav.chitchat.source

/**
 *    val title: String,               "title": "User already registered",
 *    val message: String,             "message": "User already registered. Please login.",
 *    val type: String,                "type": "error",
 *    val data: Data,                  "data": {},
 *    val code: String,                "code": "USER_EXISTS",
 *    val displayType: String          "displayType": "onscreen"
 */


data class ResponseDTO<out T>(
    val title: String? = "",
    val message: String? = "",
    val type: Int? = 0,
    val data: T?,
    val code: Int? = 0,
    val displayType: String? = ""
) {


    companion object {
        fun <T> success(
            title: String?,
            message: String?,
            type: Int?,
            data: T?,
            code: Int?,
            displayType: String?
        ): ResponseDTO<T> {
            return ResponseDTO(title, message, APIConstant.Status.SUCCESS, data, code, displayType)
        }

        fun <T> error(
            title: String? = "",
            message: String? = "",
            type: Int? = 0,
            data: T?,
            code: Int? = 0,
            displayType: String?
        ): ResponseDTO<T> {
            return ResponseDTO(title, message, APIConstant.Status.ERROR, data, code, displayType)
        }

        fun <T> executed(
            data: T? = null
        ): ResponseDTO<T> {
            return ResponseDTO(
                "",
                "done",
                APIConstant.Status.SUCCESS,
                data,
                -1,
                "onScreen"
            )
        }


        fun <T> loading(): ResponseDTO<T> {
            return ResponseDTO(
                null, null, APIConstant.Status.LOADING, null, null, null

            )
        }


    }


}

