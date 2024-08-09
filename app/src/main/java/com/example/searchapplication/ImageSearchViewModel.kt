package com.example.searchapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ImageSearchViewModel : ViewModel() {

    private val _images = MutableLiveData<List<Image>>()
    val images: LiveData<List<Image>> get() = _images

    var lastQuery: String = ""

    fun searchImages(query: String) {
        lastQuery = query
//        viewModelScope.launch {
//            val response = RetrofitInstance.api.searchImages(query)
//            if (response.isSuccessful) {
//                _images.value = response.body()?.documents?.map {
//                    Image(it.thumbnailUrl, it.displaySitename, it.datetime)
//                }
//            }
//        }
    }
}
