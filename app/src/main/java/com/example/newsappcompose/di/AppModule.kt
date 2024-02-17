package com.example.newsappcompose.di

import android.app.Application
import com.example.newsappcompose.data.manger.LocalUserMangerImpl
import com.example.newsappcompose.domain.manger.LocalUserManger
import com.example.newsappcompose.domain.usecases.AppEntryUseCases
import com.example.newsappcompose.domain.usecases.ReadAppEntry
import com.example.newsappcompose.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserMager(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )
}