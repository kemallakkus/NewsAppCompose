package com.example.newsappcompose.presentation.details

sealed interface DetailsEvent {
    data object SaveArticle : DetailsEvent
}