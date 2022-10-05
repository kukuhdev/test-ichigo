package com.eve.testichigo.view.photos.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eve.testichigo.R
import com.eve.testichigo.databinding.ItemPhotoBinding
import com.eve.testichigo.view.photos.data.remote.response.PhotosResponse

class PhotosAdapter(
    var context: Context,
    var items: List<PhotosResponse.PhotosResponseItem>,
    val listener: ItemClickListener
) : RecyclerView.Adapter<PhotosAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemPhotoBinding.bind(itemView)
    }

    interface ItemClickListener {
        fun onItemClicked(position: Int, photoId: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ItemViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = items[position]
        with(holder) {
            Glide.with(context)
                .load(data.urls?.thumb).dontAnimate()
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(binding.ivPhoto)

            binding.tvUsername.text = "@${data.user?.username}"
            binding.tvDesc.text = data.description ?: "-"

            binding.cvDetail.setOnClickListener {

            }
        }
    }
}