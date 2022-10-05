package com.eve.testichigo.view.search.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eve.testichigo.R
import com.eve.testichigo.databinding.ItemSearchBinding
import com.eve.testichigo.view.search.data.remote.response.SearchResponse

class SearchAdapter(
    var context: Context,
    var items: List<SearchResponse.Result>,
    val listener: ItemClickListener
) : RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemSearchBinding.bind(itemView)
    }

    interface ItemClickListener {
        fun onItemClicked(position: Int, photoId: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
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