package com.example.bottomappbardemo.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bottomappbardemo.databinding.CustomMovieBinding
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val context: Context,private val items:ArrayList<MovieDetails>):
RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: CustomMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieDetails) {
            binding.title.text = item.title
            Picasso.get().load(item.image).into(binding.imageView2)
            //Glide.with(context).load(item.image).into(binding.imageView2)
            binding.rating.text = item.rating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CustomMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = items.size

}