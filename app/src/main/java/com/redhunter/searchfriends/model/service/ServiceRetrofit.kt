package com.redhunter.searchfriends.model.service

import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import retrofit2.Response
import retrofit2.http.GET

interface ServiceRetrofit {

    @GET("image/random/50")
    suspend fun getAllRandomDog(): Response<ResponseDogRetrofit>

}