package ru.stas.photogallery

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.stas.photogallery.api.GalleryItem
import ru.stas.photogallery.databinding.ListItemGalleryBinding


class PhotoListAdapter(
    private val galleryItem: List<GalleryItem>,
    private val onItemClicked: (Uri) -> Unit
    )
    :RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater,parent,false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = galleryItem[position]
        holder.bind(item,onItemClicked)
    }

    override fun getItemCount(): Int = galleryItem.size

    inner class PhotoViewHolder(
        private val binding: ListItemGalleryBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(galleryItem: GalleryItem, onItemClicked: (Uri) -> Unit){
            binding.itemImageView.load(galleryItem.url){
                placeholder(R.drawable.bill_up_close)
            }
            binding.root.setOnClickListener { onItemClicked(galleryItem.photoPageUri) }
        }
    }
}