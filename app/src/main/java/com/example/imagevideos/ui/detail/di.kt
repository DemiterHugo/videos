package com.example.imagevideos.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.example.imagevideos.di.ImageId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule{

    @Provides
    @ViewModelScoped
    @ImageId
    fun provideImageId(savedStateHandle: SavedStateHandle) =
        VideoFragmentArgs.fromSavedStateHandle(savedStateHandle).imageId
}