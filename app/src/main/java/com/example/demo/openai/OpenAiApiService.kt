package com.example.demo.openai


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST


/**
 * Created by zhaohongshuai on 2023/3/2
 */

interface OpenAiApiService {

    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun getCompletion(
        @Header("Authorization") apiKey: String,
        @Body request: OpenAiRequest
    ): Call<OpenAiResponse>
}
