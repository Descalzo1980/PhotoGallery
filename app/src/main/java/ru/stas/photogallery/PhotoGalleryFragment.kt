package ru.stas.photogallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import ru.stas.photogallery.api.FlickrApi
import ru.stas.photogallery.databinding.FragmentPhotoGalleryBinding

private const val TAG = "PhotoGalleryFragment"

class PhotoGalleryFragment: Fragment() {
    private var _binding : FragmentPhotoGalleryBinding? = null
    private val binding
    get() = checkNotNull(_binding){
        "Cannot access binding because it is null. Is the view visible?"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoGalleryBinding.inflate(inflater,container,false)
        binding.photoGrid.layoutManager = GridLayoutManager(context,3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val flickrApi: FlickrApi =  retrofit.create<FlickrApi>()

        viewLifecycleOwner.lifecycleScope.launch {
            val response = flickrApi.fetchContents()
            Log.d(TAG,"Response received: $response")
        }
    }

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }
}