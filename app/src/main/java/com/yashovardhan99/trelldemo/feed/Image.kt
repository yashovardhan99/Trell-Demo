package com.yashovardhan99.trelldemo.feed

import com.google.gson.annotations.SerializedName

data class Image(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("download_url") val downloadUrl: String,
    @field:SerializedName("author") val author: String
)