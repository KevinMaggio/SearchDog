package com.redhunter.searchfriends.model.repository

import androidx.lifecycle.MutableLiveData
import com.redhunter.searchfriends.model.firebase.UserFirebase
import com.redhunter.searchfriends.utils.StateLogin

class UserRepository () {
    private val userFirebase= UserFirebase()

    fun registerUser(email:String,name:String,password:String){
        userFirebase.registerUser(email,password,name)
    }

    fun getRegisterStatus(): MutableLiveData<StateLogin> {
        return userFirebase.getResultStatus()
    }

    fun findUserByID(email:String,password:String){
        userFirebase.findUserByEmail(email,password)
    }

}