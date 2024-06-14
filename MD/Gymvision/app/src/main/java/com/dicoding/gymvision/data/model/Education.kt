package com.dicoding.gymvision.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Education(
    val name: String,
    val description: String,
    val photo: String
) : Parcelable