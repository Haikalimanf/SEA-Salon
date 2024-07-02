package com.example.seasalon.ui.reservation.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seasalon.databinding.ItemListReservationBinding
import com.example.seasalon.databinding.ItemListReservationHistoryBinding

class ListReservationAdapter : ListAdapter<DataReservation, RecyclerView.ViewHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemListReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = getItem(position)
        when (holder) {
            is UpcomingViewHolder -> holder.bind(reservation)
        }
    }

    class UpcomingViewHolder(private val binding: ItemListReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: DataReservation) {
            binding.tvNameReservation.text = "Name : ${reservation.name}"
            binding.tvServiceReservation.text = "Service : ${reservation.service}"
            binding.tvDateReservation.text = "Date : ${reservation.date}"
            binding.tvTimeReservation.text = "Time : ${reservation.time}"
            binding.tvStatus.text = "Status : ${reservation.status}"

            binding.tvCancelReservation.setOnClickListener {

            }
        }
    }


    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataReservation>() {
            override fun areItemsTheSame(oldItem: DataReservation, newItem: DataReservation): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataReservation, newItem: DataReservation): Boolean {
                return oldItem == newItem
            }
        }
    }
}
