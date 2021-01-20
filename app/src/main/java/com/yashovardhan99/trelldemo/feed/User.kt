package com.yashovardhan99.trelldemo.feed

import com.google.gson.annotations.SerializedName

data class User(
    val name: String,
    val imageUrl: String
)

data class UserResponse(@field:SerializedName("results") val results: List<UserResult>)
data class UserResult(@field:SerializedName("name") val name: UserName, val picture: UserPicture)
data class UserName(val first: String, val last: String)
data class UserPicture(val large: String)
