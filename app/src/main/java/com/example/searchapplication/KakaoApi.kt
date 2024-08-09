package com.example.searchapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: KakaoApi by lazy {
        retrofit.create(KakaoApi::class.java)
    }
}

interface KakaoApi {
    @Headers("Authorization: KakaoAK 3add3c350f638abbafbf0bf1fc75ca35")
    @GET("v2/search/image")
    fun searchImages(
        @Query("query") query: String,
        @Query("sort") sort: String = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 80,
    ): KakaoImageSearchResponse
}

data class KakaoApiOkResponse (
    val thumbnailUrl: String,
    val displaySitename: String,
    val datetime: String
)
