package com.example.imagevideos.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Image::class], version = 2, exportSchema = false)
abstract class DataBaseR: RoomDatabase() {

    abstract fun imageDao(): ImageDao
}