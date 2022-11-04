package com.raji.imagesearch.data.repository

import com.raji.imagesearch.data.request.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepository @Inject constructor(private val apiService: ApiService) {
}