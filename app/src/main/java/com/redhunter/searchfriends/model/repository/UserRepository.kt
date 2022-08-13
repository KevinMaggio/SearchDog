package com.redhunter.searchfriends.model.repository

import androidx.lifecycle.MutableLiveData
import com.redhunter.searchfriends.model.dto.firebaseDto.RegisterResponse
import com.redhunter.searchfriends.model.dto.firebaseDto.UserResponse
import com.redhunter.searchfriends.model.firebase.UserFirebase

class UserRepository () {
    private val userFirebase= UserFirebase()
    fun addUser(email:String,name:String,password:String){
        userFirebase.addUser(email,name,password)
    }

    fun getResponseAddUser(): MutableLiveData<RegisterResponse> {
        return userFirebase.getRegisterResult()
    }

    fun getAllUserFireStore() {
       userFirebase.getAllUser()
    }
    fun getResponseAllUser(): MutableLiveData<List<UserResponse>> {
        return userFirebase.getResponseAllUser()
    }

}