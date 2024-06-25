package com.example.seasalon.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.seasalon.R

class ListServiceAdapter(private val listService: List<DataServices>) : RecyclerView.Adapter<ListServiceAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_icon_service)
        val tvName: TextView = itemView.findViewById(R.id.tv_service_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_slogan_service)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_service,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listService.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listService[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
    }

}