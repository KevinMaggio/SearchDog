package com.redhunter.searchfriends.model.useCase

import androidx.lifecycle.MutableLiveData
import com.redhunter.searchfriends.model.dto.firebaseDto.RegisterResponse
import com.redhunter.searchfriends.model.dto.firebaseDto.UserResponse
import com.redhunter.searchfriends.model.repository.UserRepository

class UserRepositoryUseCase {
    val userRepository= UserRepository()
    fun addUser(email:String,name:String,password:String){
        userRepository.addUser(email,name,password)
    }

    fun getResponseAddUser(): MutableLiveData<RegisterResponse> {
        return userRepository.getResponseAddUser()
    }

    fun getAllUserFireStore() {
        userRepository.getAllUserFireStore()
    }

    fun getResponseAllUser(): MutableLiveData<List<UserResponse>> {
        return userRepository.getResponseAllUser()
    }

}