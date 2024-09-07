package no.uio.ifi.in2000.moueeda.vipps_internship_android.Data

import no.uio.ifi.in2000.moueeda.vipps_internship_android.Model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface CountryAPIService {
    @GET("countries/Pakistan")

    fun getCountryDetails(
        @Header("Authorization") authHeader: String
    ): Call<Country>
}
