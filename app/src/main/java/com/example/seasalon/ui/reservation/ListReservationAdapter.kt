package com.example.seasalon.ui.reservation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seasalon.databinding.ItemListReservationBinding
import com.example.seasalon.databinding.ItemListReservationHistoryBinding

class ListReservationAdapter : ListAdapter<DataReservation, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun getItemViewType(position: Int): Int {

        return if (getItem(position).isUpcoming) {
            VIEW_TYPE_UPCOMING
        } else {
            VIEW_TYPE_HISTORY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_UPCOMING -> {
                val binding = ItemListReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UpcomingViewHolder(binding)
            }
            VIEW_TYPE_HISTORY -> {
                val binding = ItemListReservationHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HistoryViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val reservation = getItem(position)
        when (holder) {
            is UpcomingViewHolder -> holder.bind(reservation)
            is HistoryViewHolder -> holder.bind(reservation)
        }
    }

    class UpcomingViewHolder(private val binding: ItemListReservationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: DataReservation) {
            binding.tvNameReservation.text = "Name : ${reservation.name}"
            binding.tvServiceReservation.text = "Service : ${reservation.service}"
            binding.tvDateReservation.text = "Date : ${reservation.date}"
            binding.tvTimeReservation.text = "Time : ${reservation.time}"
        }
    }

    class HistoryViewHolder(private val binding: ItemListReservationHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: DataReservation) {
            binding.tvNameReservation.text = "Name : ${reservation.name}"
            binding.tvServiceReservation.text = "Service : ${reservation.service}"
            binding.tvDateReservation.text = "Date : ${reservation.date}"
            binding.tvTimeReservation.text = "Time : ${reservation.time}"
        }
    }

    companion object {
        private const val VIEW_TYPE_UPCOMING = 0
        private const val VIEW_TYPE_HISTORY = 1

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
