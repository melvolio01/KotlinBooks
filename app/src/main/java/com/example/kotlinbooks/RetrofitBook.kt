package com.example.kotlinbooks

import com.google.gson.annotations.SerializedName

class RetrofitBook {
    @SerializedName("id")
    var id: String? = null

    var volumeInfo: VolumeInfo? = null
}