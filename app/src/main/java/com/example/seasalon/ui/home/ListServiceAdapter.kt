package com.example.seasalon.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.seasalon.R
import com.example.seasalon.databinding.ItemButtonTimeBinding
import com.example.seasalon.databinding.ItemCardServiceBinding
import com.example.seasalon.ui.auth.login.LoginActivity
import com.example.seasalon.ui.booking.BookingActivity
import com.example.seasalon.ui.booking.DataTime

class ListServiceAdapter :
    ListAdapter<DataServices, ListServiceAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCardServiceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
    }

    class MyViewHolder(private val binding: ItemCardServiceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(services: DataServices){
            Glide.with(itemView.context)
            .load(services.photo)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(binding.imgIconService)
            binding.tvServiceName.text = services.name
            binding.tvSloganService.text = services.slogan
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, BookingActivity::class.java)
                itemView.context.startActivity(intent)
            }
        }
    }


    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataServices>() {
            override fun areItemsTheSame(oldItem: DataServices, newItem: DataServices): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DataServices, newItem: DataServices): Boolean {
                return oldItem == newItem
            }
        }
    }
}