package com.redhunter.searchfriends.model.dto.retrofitDto

import com.google.gson.annotations.SerializedName

data class ResponseDogRetrofit(
    @SerializedName("message") val listDogs: List<String>,
    @SerializedName("status") val status: String
)