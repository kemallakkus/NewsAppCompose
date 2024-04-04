package com.example.newsappcompose.presentation.details

import androidx.paging.PagingData
import com.example.newsappcompose.domain.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow

class DetailsViewModel {

}

data class DetailsState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null,
)

sealed interface DetailsEvent {
    data object SaveArticle : DetailsEvent
}