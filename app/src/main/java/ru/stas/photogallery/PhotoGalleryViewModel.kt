package ru.stas.photogallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.stas.photogallery.api.GalleryItem

private const val TAG = "PhotoGalleryViewModel"

class PhotoGalleryViewModel: ViewModel() {
    private val photoRepository = PhotoRepository()

    private val _galleryItem: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItem: StateFlow<List<GalleryItem>>
    get() = _galleryItem.asStateFlow()

    init {
        viewModelScope.launch {
            try{
                val items = photoRepository.fetchPhotos()
                Log.d(TAG, "Items received: $items")
                _galleryItem.value = items
            }catch (ex: Exception){
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }

        }
    }
}