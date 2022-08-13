package com.redhunter.searchfriends.model.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.redhunter.searchfriends.model.dto.firebaseDto.RegisterResponse
import com.redhunter.searchfriends.model.dto.firebaseDto.UserResponse


class UserFirebase {
    var fireStore = FirebaseFirestore.getInstance()
    var registerLiveData = MutableLiveData(RegisterResponse(false))
    val userResponseLiveData = MutableLiveData<List<UserResponse>>()


    fun addUser(email: String, name: String, password: String) {
        fireStore.collection("users").document(email)
            .set(hashMapOf("name" to name, "email" to email, "password" to password))
            .addOnCompleteListener {
                isDataPushed(true)
            }
    }

    fun getAllUser() {
        fireStore.collection("users").get().addOnCompleteListener {
            val temp = mutableListOf<UserResponse>()
            it.result.documents.forEach { doc ->
                temp.add(
                    UserResponse(
                        doc.get("name").toString(),
                        doc.get("email").toString(),
                        doc.get("password").toString()
                    )
                )
            }
             userResponseLiveData.postValue(temp)
        }
    }

    fun getResponseAllUser(): MutableLiveData<List<UserResponse>> {
        return userResponseLiveData
    }

    fun isDataPushed(value: Boolean) {
        registerLiveData.postValue(RegisterResponse(value))
    }

    fun getRegisterResult(): MutableLiveData<RegisterResponse> {
        return registerLiveData
    }
}