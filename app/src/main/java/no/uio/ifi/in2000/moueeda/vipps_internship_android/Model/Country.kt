package no.uio.ifi.in2000.moueeda.vipps_internship_android.Model

import com.google.gson.annotations.SerializedName;

data class ApiResponse(
    @SerializedName("data")
    val country: Country
)

data class Country(
    @SerializedName("name")
    val name: String?,

    @SerializedName("full_name")
    val fullName: String?,

    @SerializedName("capital")
    val capital: String?,

    @SerializedName("current_president")
    val president: President?
)

data class President(
    @SerializedName("name")
    val name: String?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("appointment_start_date")
    val appointmentStartDate: String?,

    @SerializedName("appointment_end_date")
    val appointmentEndDate: String?
)