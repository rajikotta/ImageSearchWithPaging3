package com.raji.imagesearch.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.raji.imagesearch.data.repository.ImageRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository,
     stateHandle: SavedStateHandle
) :
    ViewModel() {

    companion object {
        const val DEFAULT_QUERY = "earth"
        const val CURRENT_QUERY = "current_query"
    }

    private val queryLiveData = stateHandle.getLiveData(CURRENT_QUERY, DEFAULT_QUERY)

    val photos = queryLiveData.switchMap {
        repository.getSearchResult(it).cachedIn(viewModelScope)
    }

    fun searchForImage(query: String) {
        queryLiveData.value = query
    }

}