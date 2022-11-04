package com.raji.imagesearch.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.raji.imagesearch.data.models.UnsplashPhoto
import com.raji.imagesearch.data.request.ApiService
import retrofit2.HttpException
import java.io.IOException

private const val UNSPLASH_INITIAL_PAGE_INDEX = 1

class ImagePagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: UNSPLASH_INITIAL_PAGE_INDEX
        return try {
            val result = apiService.searchPhotos(query, position, params.loadSize).results

            LoadResult.Page(
                data = result,
                prevKey = if (position == UNSPLASH_INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (result.isEmpty()) null else position + 1
            )
        } catch (ioEx: IOException) {
            LoadResult.Error(ioEx)
        } catch (httpEx: HttpException) {
            LoadResult.Error(httpEx)

        }
    }
}