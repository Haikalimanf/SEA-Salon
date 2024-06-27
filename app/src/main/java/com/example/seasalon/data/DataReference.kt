package com.example.seasalon.data

import com.example.seasalon.ui.booking.DataTime
import com.example.seasalon.ui.home.DataServices

object DataReference {
    val item = listOf(
        DataServices(
            "Haircut",
            "Fresh Cuts, Fresh Look.",
            "https://raw.githubusercontent.com/Haikalimanf/AssetFotoIconSalon/main/haircut_icon.png"
        ),
        DataServices(
            "Styling",
            "Transform Your Look.",
            "https://raw.githubusercontent.com/Haikalimanf/AssetFotoIconSalon/main/styling_icon.png"),
        DataServices(
            "Manicure",
            "Hand Perfection, Every Time.",
            "https://raw.githubusercontent.com/Haikalimanf/AssetFotoIconSalon/main/manicure_icon.png"),
        DataServices(
            "Pedicure",
            "Elegant Feet, Every Step.",
            "https://raw.githubusercontent.com/Haikalimanf/AssetFotoIconSalon/main/pedicure_icon.png"),
        DataServices(
            "Facial",
            "Instant Glow, Lasting Beauty!",
            "https://raw.githubusercontent.com/Haikalimanf/AssetFotoIconSalon/main/facial_icon.png")
    )

    val time = listOf(
        DataTime(
            "09:00 AM",
        ),
        DataTime(
            "10:00 AM",
        ),
        DataTime(
            "11:00 AM",
        ),
        DataTime(
            "01:00 PM",
        ),
        DataTime(
            "02:00 PM",
        ),
        DataTime(
            "04:00 PM",
        ),
        DataTime(
            "05:00 PM",
        ),
        DataTime(
            "07:00 PM",
        ),
        DataTime(
            "08:00 PM",
        ),
    )
}