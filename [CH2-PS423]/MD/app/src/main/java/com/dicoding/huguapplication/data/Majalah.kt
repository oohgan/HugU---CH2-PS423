package com.dicoding.huguapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Majalah(
    var name: String = "",
    var poster: Int ?,
    var description: String =""
) : Parcelable