package com.redhunter.searchfriends.model.dataSource

import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.service.ServiceRetrofit
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogDataSource {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dog.ceo/api/breeds/")
        .build()

    private val serviceImplement= retrofit.create(ServiceRetrofit::class.java)


    suspend fun getAllDogRandom(): Response<ResponseDogRetrofit> {
       return serviceImplement.getAllRandomDog()
    }
}