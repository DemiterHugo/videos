package com.example.imagevideos.di

import android.app.Application
import androidx.room.Room
import com.example.imagevideos.R
import com.example.imagevideos.data.AndroidPermissionChecker
import com.example.imagevideos.data.PlayServicesLocationDataSource
import com.example.imagevideos.data.database.DataBaseR
import com.example.imagevideos.data.database.RoomImageDataSource
import com.example.imagevideos.data.datasource.LocalImageDataSource
import com.example.imagevideos.data.datasource.LocationDataSource
import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.data.repositories.PermissionChecker
import com.example.imagevideos.data.server.ServerImageDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDataBase(app: Application) = Room.databaseBuilder(
        app,
        DataBaseR::class.java,
        "database"
    ).build()

    @Provides
    @Singleton
    fun provideImageDao(db: DataBaseR) = db.imageDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule{

    @Binds
    abstract fun bindLocalImageDataSource(localImageDataSource: RoomImageDataSource): LocalImageDataSource

    @Binds
    abstract fun bindRemoteImageDataSource(remoteImageDataSource: ServerImageDataSource): RemoteImageDataSource

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: PlayServicesLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindPermissionChecker(permissionChecker: AndroidPermissionChecker): PermissionChecker
}