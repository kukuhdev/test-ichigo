package com.eve.testichigo.view.photos.di

import com.eve.testichigo.core.di.NetworkModule
import com.eve.testichigo.view.photos.data.remote.api.PhotoApi
import com.eve.testichigo.view.photos.data.repository.PhotosRepositoryImpl
import com.eve.testichigo.view.photos.domain.PhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class PhotosModule {

    @Singleton
    @Provides
    fun providePhotosApi(retrofit: Retrofit) : PhotoApi {
        return retrofit.create(PhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun providePhotosRepository(photoApi: PhotoApi) : PhotosRepository {
        return PhotosRepositoryImpl(photoApi)
    }


}