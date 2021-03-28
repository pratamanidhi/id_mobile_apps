package com.tugas_akhir.tambal_ban.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data  class TambalBanModel(
        val id : String? = "",

        val nama_tambal_ban : String? = "",

        val address : String? = "",

        val price : String? = "",

        val operation : String? = "",

        val latitude : String? = "",

        val longitude : String? = ""


) : Parcelable