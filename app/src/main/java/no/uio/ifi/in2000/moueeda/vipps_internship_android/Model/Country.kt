package no.uio.ifi.in2000.moueeda.vipps_internship_android.Model

import com.google.gson.annotations.SerializedName;

data class Country(
    @SerializedName("name")
    val name: String?,

    @SerializedName("capital")
    val capital: String?,

    @SerializedName("current_president")
    val president: String?
)