package com.eve.testichigo.core.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //https://eng-nohasamirsaad.medium.com/dependency-injection-with-hilt-e73705e1f27b
    @Singleton
    @Provides
    fun provideGlide(@ActivityContext context: Context): RequestManager = Glide.with(context)
}