package com.example.newsappcompose.di

import android.app.Application
import androidx.room.Room
import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.data.local.NewsDatabase
import com.example.newsappcompose.data.local.NewsTypeConverter
import com.example.newsappcompose.data.manger.LocalUserMangerImpl
import com.example.newsappcompose.data.remote.NewsApi
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.manger.LocalUserManger
import com.example.newsappcompose.domain.repository.NewsRepository
import com.example.newsappcompose.domain.usecases.appentry.AppEntryUseCases
import com.example.newsappcompose.domain.usecases.appentry.ReadAppEntry
import com.example.newsappcompose.domain.usecases.appentry.SaveAppEntry
import com.example.newsappcompose.domain.usecases.news.DeleteArticle
import com.example.newsappcompose.domain.usecases.news.GetNews
import com.example.newsappcompose.domain.usecases.news.NewsUseCases
import com.example.newsappcompose.domain.usecases.news.SearchNews
import com.example.newsappcompose.domain.usecases.news.SelectArticle
import com.example.newsappcompose.domain.usecases.news.SelectArticles
import com.example.newsappcompose.domain.usecases.news.UpsertArticle
import com.example.newsappcompose.util.Constants.BASE_URL
import com.example.newsappcompose.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)

        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}