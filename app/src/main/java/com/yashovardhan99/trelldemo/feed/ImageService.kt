package com.yashovardhan99.trelldemo.feed

import com.yashovardhan99.trelldemo.profile.ImageInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<Image>>

    @GET("v2/list")
    fun getImages1(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Call<List<ImageInfo>>
}