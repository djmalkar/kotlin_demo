package com.dipesh.kotlin.dependencyinjection.presentation

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
class ActivityModule {

    @Provides
    @ActivityScoped
    fun provideLayoutInflater(context: Activity) : LayoutInflater {
        return LayoutInflater.from(context)
    }

    @Provides
    @ActivityScoped
    fun provideFragmentManager(activity: AppCompatActivity): FragmentManager {
        return activity.supportFragmentManager
    }
}