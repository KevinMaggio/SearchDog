package com.redhunter.searchfriends.model.useCase

import androidx.lifecycle.MutableLiveData
import com.redhunter.searchfriends.model.repository.UserRepository
import com.redhunter.searchfriends.utils.StateLogin

class UserRepositoryUseCase {
    private val userRepository= UserRepository()

    fun registerUser(email:String,name:String,password:String){
        userRepository.registerUser(email,password,name)
    }

    fun getRegisterStatus(): MutableLiveData<StateLogin> {
        return userRepository.getRegisterStatus()
    }

    fun loginFireStore(email:String,password:String){
        userRepository.findUserByID(email,password)
    }

}