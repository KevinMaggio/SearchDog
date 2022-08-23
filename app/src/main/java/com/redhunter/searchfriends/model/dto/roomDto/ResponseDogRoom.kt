package com.redhunter.searchfriends.model.dto.roomDto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.redhunter.searchfriends.model.dto.retrofitDto.Status

@Entity
data class ResponseDogRoom(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")var id: Int= 0,
    @ColumnInfo(name = "messages") var listDogs: List<String>?= null,
    @ColumnInfo(name = "status") var status: String?= null
)