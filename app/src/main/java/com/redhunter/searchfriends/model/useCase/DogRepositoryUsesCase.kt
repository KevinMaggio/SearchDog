package com.redhunter.searchfriends.model.useCase

import android.app.Application
import com.redhunter.searchfriends.model.dto.dog.DogModel
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.repository.DogRepository
import com.redhunter.searchfriends.utils.Permission
import retrofit2.Response

class DogRepositoryUsesCase(private val application: Application) {
    private val dogRepository= DogRepository(application)

    suspend fun getAllDogRandom(permission: Permission): DogModel {
        return dogRepository.getAllDogRandom(permission)
    }

    suspend fun postDog(){
        dogRepository.postDogs()
    }
}