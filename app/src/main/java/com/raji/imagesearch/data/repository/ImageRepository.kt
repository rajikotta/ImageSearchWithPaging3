package com.raji.imagesearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.raji.imagesearch.data.request.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(private val apiService: ApiService) {

    fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 1,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ImagePagingSource(apiService, query)
            }
        ).liveData
}