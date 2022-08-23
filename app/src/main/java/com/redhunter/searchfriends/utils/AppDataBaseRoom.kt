package com.redhunter.searchfriends.utils

import android.app.Application
import androidx.room.*
import com.redhunter.searchfriends.model.dto.roomDto.ResponseDogRoom
import com.redhunter.searchfriends.model.room.DogDao

@Database(entities = [ResponseDogRoom::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDataBaseRoom : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        fun getInstance(application: Application): AppDataBaseRoom {
            return Room.databaseBuilder(
                application,
                AppDataBaseRoom::class.java,
                "search-friends-bbdd"
            ).build()
        }
    }
}