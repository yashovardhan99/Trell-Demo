package com.yashovardhan99.trelldemo.feed

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersService {
    @GET("api/?inc=name,picture")
    suspend fun getUsers(@Query("results") count: Int = 10): Response<UserResponse>
}