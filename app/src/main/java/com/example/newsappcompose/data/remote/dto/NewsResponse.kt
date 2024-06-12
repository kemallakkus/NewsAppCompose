package com.example.newsappcompose.data.remote.dto

import com.example.newsappcompose.domain.model.Article

data class NewsResponse(
    val articles: List<Article>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)
