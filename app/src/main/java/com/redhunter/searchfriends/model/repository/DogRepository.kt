package com.redhunter.searchfriends.model.repository

import android.app.Application
import com.redhunter.searchfriends.model.dataSource.DogDataSource
import com.redhunter.searchfriends.model.dto.dog.DogModel
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseSingleDogRetrofit
import com.redhunter.searchfriends.utils.Permission
import retrofit2.Response

class DogRepository(private val application: Application) {

    private val dogDataSource = DogDataSource(application)

    suspend fun getAllDogRandom(permission: Permission): DogModel {
        return dogDataSource.getAllDogRandom(permission)
    }

    suspend fun postDogs() {
        dogDataSource.postDogToRoom()
    }

    suspend fun searchDogByBreed(breed: String): Response<ResponseSingleDogRetrofit> {
        return dogDataSource.searchDog(breed)
    }

    suspend fun getOneDog(): Response<ResponseSingleDogRetrofit> {
        return dogDataSource.getOneDog()
    }
}