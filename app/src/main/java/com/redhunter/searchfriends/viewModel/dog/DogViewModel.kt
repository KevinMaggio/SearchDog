package com.redhunter.searchfriends.viewModel.dog

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redhunter.searchfriends.model.dto.dog.DogModel
import com.redhunter.searchfriends.model.useCase.DogRepositoryUsesCase
import com.redhunter.searchfriends.utils.Permission
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogViewModel(private val application: Application) : ViewModel() {

    private val dogUsesCase = DogRepositoryUsesCase(application)
    val allDogRandomData = MutableLiveData<DogModel>()

    fun getAllDogRandom(permission: Permission) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = dogUsesCase.getAllDogRandom(permission)
            if (call.status.name == "SUCCESS") {
                allDogRandomData.postValue(call)
            }
        }
    }

    fun postDogsToBBDD() {
        CoroutineScope(Dispatchers.IO).launch {
            dogUsesCase.postDog()
        }
    }

}