package com.notesapi.di

import com.google.gson.GsonBuilder
import com.notesapi.apis.UserApiInterface
import com.utils.constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideretrofir():Retrofit{
        return Retrofit.Builder()
            .baseUrl(constants.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun createUserApi(retrofit:Retrofit):UserApiInterface{
        return retrofit.create(UserApiInterface::class.java)
    }

}