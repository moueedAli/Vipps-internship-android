package no.uio.ifi.in2000.moueeda.vipps_internship_android

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import no.uio.ifi.in2000.moueeda.vipps_internship_android.Model.Country
import androidx.compose.foundation.layout.Column
import no.uio.ifi.in2000.moueeda.vipps_internship_android.Model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CountryDetailsScreen(modifier: Modifier = Modifier) {
    var country by remember { mutableStateOf<Country?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    val authToken = "Bearer 1413|cqWtDnePqhfUBzwa0HfxAbXdHH9S1ZJbfoJ6iLWu"
    val apiService = CountryApiSerivceInstance.apiService

    LaunchedEffect(Unit) {
        val countryName = "Afghanistan" // Må erstatte denne slik at brukeren skriver inn og vi henter data
        apiService.getCountryDetails(authToken, countryName).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    country = response.body()?.country
                } else {
                    errorMessage = "Response not successful: " + response.code()
                    Log.d("API Response", "Response code: ${response.code()} Body: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                errorMessage = "Failed to fetch data"
                Log.d("API Failure", t.message ?: "Unknown error")
            }
        })
    }

    Column(modifier) {
        country?.let {
            Text(text = "Name: ${it.name}")
            Text(text = "Full Name: ${it.fullName}")
            Text(text = "Capital: ${it.capital}")
            Text(text = "President: ${it.president?.name}")
        } ?: run {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage)
            } else {
                Text(text = "Loading...")
            }
        }
    }
}
