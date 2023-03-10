package ru.stas.photogallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import ru.stas.photogallery.api.GalleryItem
import ru.stas.photogallery.databinding.ListItemGalleryBinding


class PhotoListAdapter(private val galleryItem: List<GalleryItem>)
    :RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater,parent,false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = galleryItem[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = galleryItem.size


    inner class PhotoViewHolder(
        private val binding: ListItemGalleryBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(galleryItem: GalleryItem){
            binding.itemImageView.load(galleryItem.url){
                placeholder(R.drawable.bill_up_close)
//                transformations(
//                    CircleCropTransformation()
//                )
            }
        }
    }
}