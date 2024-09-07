package no.uio.ifi.in2000.moueeda.vipps_internship_android

import no.uio.ifi.in2000.moueeda.vipps_internship_android.Data.CountryAPIService

object CountryApiSerivceInstance {
    val apiService: CountryAPIService by lazy {
        RetrofitClient.instance.create(CountryAPIService::class.java)
    }
}