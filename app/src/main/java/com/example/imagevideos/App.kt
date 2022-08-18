package com.example.imagevideos

import android.app.Application
import androidx.room.Room
import com.example.imagevideos.data.database.DataBaseR

class App: Application() {

    lateinit var db: DataBaseR

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, DataBaseR::class.java,"database").build()
    }
}