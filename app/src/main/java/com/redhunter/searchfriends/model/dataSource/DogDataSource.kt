package com.redhunter.searchfriends.model.dataSource

import android.app.Application
import com.redhunter.searchfriends.model.dto.dog.DogModel
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseSingleDogRetrofit
import com.redhunter.searchfriends.model.dto.retrofitDto.Status
import com.redhunter.searchfriends.model.dto.roomDto.ResponseDogRoom
import com.redhunter.searchfriends.model.service.ServiceRetrofit
import com.redhunter.searchfriends.utils.AppDataBaseRoom
import com.redhunter.searchfriends.utils.Permission
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class DogDataSource(application: Application) {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dog.ceo/api/")
        .build()
    private val serviceImplement = retrofit.create(ServiceRetrofit::class.java)
    private val daoImplement = AppDataBaseRoom.getInstance(application).dogDao()

    suspend fun getAllDogRandom(permission: Permission): DogModel {
        return when (permission) {
            Permission.COMPLETE -> {
                val call = serviceImplement.getAllRandomDog()
                DogModel(call.body()?.listDogs ?: listOf(), getState(call.body()?.status!!))
            }
            else -> {
                val call = daoImplement.getAllDog()
                try {
                    DogModel(call.listDogs ?: listOf(), getState("success"))
                } catch (e: Exception) {
                    DogModel(listOf(), getState("error"))
                }
            }
        }
    }

    suspend fun getOneDog(): Response<ResponseSingleDogRetrofit> {
        return serviceImplement.getOneDog()
    }

    suspend fun searchDog(breed: String): Response<ResponseSingleDogRetrofit> {
        return serviceImplement.searchDogByBreed(breed)
    }

    suspend fun postDogToRoom() {
        val call = serviceImplement.getAllRandomDog()
        val value = ResponseDogRoom(0, call.body()?.listDogs ?: listOf(), "success")
        daoImplement.insertAll(value)
    }

    fun getState(state: String): Status {
        return when (state) {
            "success" -> {
                Status.SUCCESS
            }
            else -> {
                Status.ERROR
            }
        }
    }
}