package com.example.imagevideos.model.network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


//RemoteConnection
object ApiClient {

    private val okHttpClient = HttpLoggingInterceptor().run{
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    private val builder = Retrofit.Builder()
        .baseUrl("https://pixabay.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val imageService: ImageService = builder.create()
    val videoService: VideoService = builder.create()
}