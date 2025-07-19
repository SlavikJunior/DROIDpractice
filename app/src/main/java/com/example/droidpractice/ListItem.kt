package com.example.droidpractice

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListItem(
    val id: Int,
    val title: String,
    val description: String,
    var imageUrl: String
) : Parcelable