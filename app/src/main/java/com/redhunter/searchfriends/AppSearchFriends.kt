package com.redhunter.searchfriends

import android.app.Application
import com.redhunter.searchfriends.utils.Preferences

class AppSearchFriends : Application() {

    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = Preferences(applicationContext)
    }
}