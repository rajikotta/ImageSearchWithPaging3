package com.raji.imagesearch.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.raji.imagesearch.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val repository: ImageRepository) :
    ViewModel() {

    companion object {
        const val DEFAULT_QUERY = "earth"
    }

    private val queryLiveData = MutableLiveData(DEFAULT_QUERY)

    val photos = queryLiveData.switchMap {
        repository.getSearchResult(it).cachedIn(viewModelScope)
    }

    fun searchForImage(query: String) {
        queryLiveData.value = query
    }

}