package com.dipesh.kotlin.dependencyinjection.application

import android.content.Context
import androidx.room.Room
import com.dipesh.kotlin.data.local.AppDatabase
import com.dipesh.kotlin.data.local.DbHelper
import com.dipesh.kotlin.data.local.DbHelperImpl
import com.dipesh.kotlin.data.remote.ApiRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "shaadi.db")
                .build()
    }

    @Singleton
    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun getRetrofitBuilder(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://randomuser.me")
                .build()
    }

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiRetrofit {
        return retrofit.create(ApiRetrofit::class.java)
    }

    @Provides
    @Singleton
    fun getDbHelper(appDatabase: AppDatabase): DbHelper {
        return DbHelperImpl(appDatabase)
    }
}