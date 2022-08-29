package com.redhunter.searchfriends.model.service

import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseSingleDogRetrofit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceRetrofit {

    @GET("breeds/image/random/50")
    suspend fun getAllRandomDog(): Response<ResponseDogRetrofit>

    @GET("breed/{id}/images/random")
    suspend fun searchDogByBreed(@Path("id") breed: String): Response<ResponseSingleDogRetrofit>

    @GET("breeds/image/random")
    suspend fun getOneDog(): Response<ResponseSingleDogRetrofit>
}