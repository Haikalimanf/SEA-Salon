package com.example.seasalon.ui.booking

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.seasalon.databinding.ItemButtonTimeBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

class ListTimeAdapter(
    val context: Context,
    val callback: OnTimeSelectedListener,
    var selectedDate: Calendar,
    val items: List<String>,
    ) :
    RecyclerView.Adapter<ListTimeAdapter.ButtonViewHolder>() {

    interface OnTimeSelectedListener {
        fun onTimeSelected(time: String)
    }

    private var selectedItemPosition: Int = -1

    inner class ButtonViewHolder(val binding: ItemButtonTimeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val binding =
            ItemButtonTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: ButtonViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val time = items[position]
        holder.binding.titleService.text = items[position]

        // Logika untuk membatasi waktu berdasarkan tanggal yang dipilih
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        val itemTime = LocalTime.parse(time, formatter)
        val currentTime = LocalTime.now()

        // Menentukan apakah tanggal yang dipilih adalah hari ini
        val calendarToday = Calendar.getInstance()
        val isToday = calendarToday.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR) &&
                calendarToday.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR)

        if (isToday && currentTime.isAfter(itemTime.plusHours(-1))) {
            holder.binding.titleService.isEnabled = false
            holder.binding.titleService.setBackgroundColor(Color.GRAY)
        } else {
            holder.binding.titleService.isEnabled = true
            holder.binding.titleService.setBackgroundColor(if (position == selectedItemPosition) Color.GREEN else Color.WHITE)
        }

        holder.binding.root.setOnClickListener {
            if (holder.binding.titleService.isEnabled) {
                val previousItemPosition = selectedItemPosition
                selectedItemPosition = position

                // Notifikasi perubahan pada item yang dipilih sebelumnya dan yang baru dipilih
                notifyItemChanged(previousItemPosition)
                notifyItemChanged(selectedItemPosition)

                // Mengirim data waktu yang dipilih ke MainActivity
                callback.onTimeSelected(time)
            } else {
                Toast.makeText(context, "Selected time is not available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setDate(selectedDate: Calendar) {
        this.selectedDate = selectedDate
    }

    override fun getItemCount(): Int {
        return items.size
    }


}
