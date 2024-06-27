package com.example.seasalon.ui.booking

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seasalon.databinding.ItemButtonTimeBinding

class ListTimeAdapter :
    ListAdapter<DataTime, ListTimeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemButtonTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        holder.bind(story)
    }

    class MyViewHolder(private val binding: ItemButtonTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: DataTime){
            binding.btnTime.text = story.time
            }
        }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataTime>() {
            override fun areItemsTheSame(oldItem: DataTime, newItem: DataTime): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: DataTime, newItem: DataTime): Boolean {
                return oldItem == newItem
            }
        }
    }
}