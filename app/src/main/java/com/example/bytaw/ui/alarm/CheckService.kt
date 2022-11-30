package com.example.bytaw.ui.alarm

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface CheckService {

    @GET("k/v1/app.json")
    suspend fun get(
        @Query("id") id: String
    ): Response<AppInformationResponse>
}


data class AppInformationResponse(
    // 変数名とJSONのキー名を一致させている
    val appId: String,
    val code: String?,
    val name: String,
    val description: String?,
    val spaceId: String?,
    val threadId: String?,
    val createdAt: String,
    val creator: Pair<String?,String?>,
    val modifiedAt: String,
    val modifier: Pair<String?,String?>
)