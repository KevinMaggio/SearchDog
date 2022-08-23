package com.redhunter.searchfriends.model.dto.dog

import com.redhunter.searchfriends.model.dto.retrofitDto.Status

data class DogModel(
    val listDogs: List<String>,
    val status: Status
)
