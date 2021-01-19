package com.yashovardhan99.trelldemo.feed

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<Image>>
}