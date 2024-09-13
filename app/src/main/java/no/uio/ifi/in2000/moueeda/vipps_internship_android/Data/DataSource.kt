package no.uio.ifi.in2000.moueeda.vipps_internship_android.Data

import no.uio.ifi.in2000.moueeda.vipps_internship_android.Model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface CountryAPIService {
    @GET("countries/{country}")

    fun getCountryDetails(
        @Header("Authorization") authHeader: String,
        @Path("country") country: String
    ): Call<ApiResponse>
}
