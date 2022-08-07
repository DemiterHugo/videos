package com.example.imagevideos

import android.app.Application
import androidx.room.Room
import com.example.imagevideos.model.database.DataBase

class App: Application() {

    private lateinit var db: DataBase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, DataBase::class.java,"database").build()
    }
}