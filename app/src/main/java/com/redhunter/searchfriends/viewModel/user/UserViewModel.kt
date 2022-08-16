package com.redhunter.searchfriends.viewModel.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redhunter.searchfriends.model.useCase.UserRepositoryUseCase
import com.redhunter.searchfriends.utils.StateLogin
import java.util.regex.Matcher


class UserViewModel  () : ViewModel(){
    private val userRepositoryUsesCase= UserRepositoryUseCase()
    var validateFieldData = MutableLiveData(false)

    fun registerUser(email:String,password:String,name:String){
        userRepositoryUsesCase.registerUser(email,password,name)
    }

    fun getDataRegisterUser(): MutableLiveData<StateLogin> {
        val data = MutableLiveData<StateLogin>()
        userRepositoryUsesCase.getRegisterStatus().observeForever{
            data.postValue(it)
        }
        return data
    }

    fun loginFireStore(email:String,password:String){
        userRepositoryUsesCase.loginFireStore(email,password)
    }

    fun validateFieldsLogin(email:String, password: String){
        validateFieldData.postValue(checkEmail(email) && checkPassword(password))
    }

    fun validateFieldsRegister(email:String, password: String, name: String){
        validateFieldData.postValue(checkEmail(email) && checkPassword(password) && checkName(name))
    }

    private fun checkName(name:String): Boolean {
        return (name.isNotEmpty())
    }

    private fun checkPassword(password:String): Boolean {
        return password.isNotEmpty()
    }

    private fun checkEmail(email:String): Boolean {
        return if (email.isNotEmpty()) {
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }else{
            false
        }
    }

}