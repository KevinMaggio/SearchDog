package com.redhunter.searchfriends.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.redhunter.searchfriends.model.dto.roomDto.ResponseDogRoom

@Dao
interface DogDao {
    @Query("SELECT * FROM ResponseDogRoom")
   suspend fun getAllDog(): ResponseDogRoom

    @Insert
   suspend fun insertAll(vararg dog: ResponseDogRoom)
}