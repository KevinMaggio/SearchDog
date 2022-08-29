package com.redhunter.searchfriends.model.dto.retrofitDto

import com.google.gson.annotations.SerializedName

data class ResponseSingleDogRetrofit (
    @SerializedName("message") val dog: String,
    @SerializedName("status") val status: String
)