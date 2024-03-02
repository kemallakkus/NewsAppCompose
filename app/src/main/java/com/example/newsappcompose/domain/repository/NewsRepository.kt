package com.example.newsappcompose.domain.repository

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

}