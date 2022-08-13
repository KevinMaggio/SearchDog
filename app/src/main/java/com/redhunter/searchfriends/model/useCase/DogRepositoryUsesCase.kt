package com.redhunter.searchfriends.model.useCase

import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.repository.DogRepository
import retrofit2.Response

class DogRepositoryUsesCase {
    private val dogRepository= DogRepository()

    suspend fun getAllDogRandom(): Response<ResponseDogRetrofit> {
        return dogRepository.getAllDogRandom()
    }
}