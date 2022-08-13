package com.redhunter.searchfriends.viewModel.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redhunter.searchfriends.model.dto.firebaseDto.RegisterResponse
import com.redhunter.searchfriends.model.dto.firebaseDto.UserResponse
import com.redhunter.searchfriends.model.useCase.UserRepositoryUseCase


class UserViewModel  () : ViewModel(){
    private val userRepositoryUsesCase= UserRepositoryUseCase()

    fun registerUser(email:String,password:String,name:String){
        userRepositoryUsesCase.addUser(email,password,name)
    }


    fun getDataRegisterUser(): MutableLiveData<RegisterResponse> {
        val data = MutableLiveData<RegisterResponse>()
        userRepositoryUsesCase.getResponseAddUser().observeForever{
            data.postValue(it)
        }
        return data
    }

    fun getAllUser(){
        userRepositoryUsesCase.getAllUserFireStore()
    }

    fun checkLoginUser(): MutableLiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        userRepositoryUsesCase.getResponseAllUser().observeForever{
            //
        }
        return data
    }



}