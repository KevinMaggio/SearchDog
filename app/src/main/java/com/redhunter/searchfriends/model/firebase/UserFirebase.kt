package com.redhunter.searchfriends.model.firebase

import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.redhunter.searchfriends.utils.StateLogin


class UserFirebase {
    var fireStore = FirebaseFirestore.getInstance()
    var statusResponse = MutableLiveData(StateLogin.EMPTY)

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
            }
            if (temp == StateLogin.LOADING) {
                temp = StateLogin.ERROR
            }
            when (temp) {
                StateLogin.ERROR -> {
                    statusResponse.postValue(StateLogin.ERROR)
                }
                StateLogin.SUCCESS -> {
                    statusResponse.postValue(StateLogin.SUCCESS)
                }
                StateLogin.ERROR_PASS -> {
                    statusResponse.postValue(StateLogin.ERROR_PASS)
                }
                else -> {
                    statusResponse.postValue(StateLogin.LOADING)
                }
            }
        }
    }

//    fun findUserByEmail(email: String, password: String) {
//        fireStore.collection("users").document(email).get().addOnCompleteListener {
//            if (email == it.result.get("email") && password == it.result.get("password")       ) {
//                statusResponse.postValue(StateLogin.SUCCESS)
//            } else {
//                statusResponse.postValue(StateLogin.ERROR_PASS)
//            }
//        }
//    }

    fun getResultStatus(): MutableLiveData<StateLogin> {
        return statusResponse
    }

}
