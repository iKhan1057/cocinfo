package com.cocdetails.di

import com.cocdetails.network.CocDetailsApi
import com.cocdetails.utils.AppUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesCocDetailsApi() = Retrofit.Builder().baseUrl(AppUtils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CocDetailsApi::class.java)

}