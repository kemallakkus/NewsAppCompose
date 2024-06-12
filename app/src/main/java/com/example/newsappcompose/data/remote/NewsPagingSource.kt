package com.example.newsappcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsappcompose.domain.model.Article

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page = page, sources = sources)
            val articleCount = newsResponse.articles?.size ?: 0
            totalNewsCount += articleCount
            val articles = newsResponse.articles?.distinctBy { it.title }
            LoadResult.Page(
                data = articles.orEmpty(),  // Handle nullability by providing an empty list if articles is null
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}