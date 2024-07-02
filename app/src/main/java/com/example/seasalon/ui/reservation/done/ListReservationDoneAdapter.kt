package com.example.seasalon.ui.reservation.done

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seasalon.databinding.ItemListReservationHistoryBinding

class ListReservationDoneAdapter : ListAdapter<DataReservationDone, ListReservationDoneAdapter.ReservationViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding = ItemListReservationHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = getItem(position)
        holder.bind(reservation)
    }

    class ReservationViewHolder(private val binding: ItemListReservationHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(reservation: DataReservationDone) {
            binding.tvNameReservation.text = "Name : ${reservation.name}"
            binding.tvServiceReservation.text = "Service : ${reservation.service}"
            binding.tvDateReservation.text = "Date : ${reservation.date}"
            binding.tvTimeReservation.text = "Time : ${reservation.time}"
            binding.tvStatus.text = "Status : ${reservation.status}"
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataReservationDone>() {
            override fun areItemsTheSame(oldItem: DataReservationDone, newItem: DataReservationDone): Boolean {
                return oldItem.date == newItem.date && oldItem.time == newItem.time
            }

            override fun areContentsTheSame(oldItem: DataReservationDone, newItem: DataReservationDone): Boolean {
                return oldItem == newItem
            }
        }
    }
}
