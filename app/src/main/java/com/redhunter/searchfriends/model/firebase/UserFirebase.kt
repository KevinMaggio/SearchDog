package com.redhunter.searchfriends.model.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.redhunter.searchfriends.utils.StateLogin


class UserFirebase {
    private var fireStore = FirebaseFirestore.getInstance()
    private var statusResponse = MutableLiveData(StateLogin.EMPTY)

    fun registerUser(email: String, password: String, name: String) {
        var temp = StateLogin.LOADING
        fireStore.collection("users").get().addOnSuccessListener {
            it.documents.forEach { doc ->
                if (doc.get("email") == email) {
                    temp = StateLogin.ERROR
                }
            }
            if (temp == StateLogin.LOADING) {
                temp = StateLogin.SUCCESS
            }
            when (temp) {
                StateLogin.ERROR -> {
                    statusResponse.postValue(StateLogin.ERROR)
                }
                StateLogin.SUCCESS -> {
                    fireStore.collection("users").document(email)
                        .set(hashMapOf("name" to name, "email" to email, "password" to password))
                    statusResponse.postValue(StateLogin.SUCCESS)
                }
                else -> {
                    statusResponse.postValue(StateLogin.LOADING)
                }
            }
        }
    }

    fun findUserByEmail(email: String, password: String) {
        var temp = StateLogin.LOADING
        fireStore.collection("users").get().addOnSuccessListener {
            it.documents.forEach { doc ->
                if (doc.get("email") == email && doc.get("password") == password) {
                    temp = StateLogin.SUCCESS
                }
                if (doc.get("email") == email && doc.get("password") != password){
                    temp = StateLogin.ERROR_PASS
                }
                if (email == "empty"){
                    temp = StateLogin.EMPTY
                }
            }
            if (temp == StateLogin.LOADING) {
                temp = StateLogin.NO_REGISTER
            }
            when (temp) {
                StateLogin.NO_REGISTER -> {
                    statusResponse.postValue(StateLogin.NO_REGISTER)
                }
                StateLogin.SUCCESS -> {
                    statusResponse.postValue(StateLogin.SUCCESS)
                }
                StateLogin.ERROR_PASS -> {
                    statusResponse.postValue(StateLogin.ERROR_PASS)
                }
                StateLogin.EMPTY ->{
                    statusResponse.postValue(StateLogin.EMPTY)
                }
                else -> {
                    statusResponse.postValue(StateLogin.LOADING)
                }
            }
        }
    }

    fun getResultStatus(): MutableLiveData<StateLogin> {
        return statusResponse
    }
}
