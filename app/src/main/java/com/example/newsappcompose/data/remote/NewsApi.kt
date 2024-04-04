package com.example.newsappcompose.data.remote

import com.example.newsappcompose.data.remote.dto.NewsResponse
import com.example.newsappcompose.util.Constants.API_KEY
import com.example.newsappcompose.util.Endpoints.EVERYTHING
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(EVERYTHING)
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponse

    @GET(EVERYTHING)
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponse
}