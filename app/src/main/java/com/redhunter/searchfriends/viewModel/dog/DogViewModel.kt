package com.redhunter.searchfriends.viewModel.dog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redhunter.searchfriends.model.dto.retrofitDto.ResponseDogRetrofit
import com.redhunter.searchfriends.model.useCase.DogRepositoryUsesCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DogViewModel: ViewModel() {

    private val dogUsesCase= DogRepositoryUsesCase()
    val allDogRandomData= MutableLiveData<ResponseDogRetrofit>()


    fun getAllDogRandom(){
        CoroutineScope(Dispatchers.IO).launch {
           val call = dogUsesCase.getAllDogRandom()
            if (call.isSuccessful){
                allDogRandomData.postValue(call.body())
            }
        }
    }

}