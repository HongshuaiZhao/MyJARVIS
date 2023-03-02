package com.example.demo.openai

/**
 * Created by zhaohongshuai on 2023/3/2
 */
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OpenAiApiClient {

    private val retrofit: Retrofit

    init {
//        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }

        val httpClient = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun getCompletion(
        apiKey: String,
        request: OpenAiRequest,
        onResponse: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val apiService = retrofit.create(OpenAiApiService::class.java)
        val call = apiService.getCompletion(apiKey, request)
        call.enqueue(object : Callback<OpenAiResponse> {
            override fun onResponse(
                call: Call<OpenAiResponse>,
                response: Response<OpenAiResponse>
            ) {
                if (response.isSuccessful) {
                    val id = response.body()?.id
                    val objectName = response.body()?._object
//                    val model = response.body()?.model
                    val created = response.body()?.created
                    val choice = response.body()?.choices?.get(0)
                    val text = choice?.message?.content ?: ""
                    onResponse(text)
                } else {
                    onError(Exception(response.message()))
                }
            }

            override fun onFailure(call: Call<OpenAiResponse>, t: Throwable) {
                onError(t)
            }
        })
    }
}
