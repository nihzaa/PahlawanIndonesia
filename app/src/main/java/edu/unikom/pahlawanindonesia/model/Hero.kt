package edu.unikom.pahlawanindonesia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    var name: String,
    var deskripsi: String,
    var photo: Int
): Parcelable
