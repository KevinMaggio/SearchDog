package com.redhunter.searchfriends.model.repository

import com.redhunter.searchfriends.model.dataSource.DogDataSource
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import retrofit2.Response

class DogRepository {

    private val dogDataSource = DogDataSource()

    suspend fun getAllDogRandom(): Response<ResponseDogRetrofit> {
        return dogDataSource.getAllDogRandom()
    }
}