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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.uio.ifi.in2000.moueeda.vipps_internship_android.Model.ApiResponse
import no.uio.ifi.in2000.moueeda.vipps_internship_android.ui.theme.VippsinternshipandroidTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CountryDetailsScreen(modifier: Modifier = Modifier) {
    var country by remember { mutableStateOf<Country?>(null) }
    var errorMessage by remember { mutableStateOf("") }
    var countryName by remember { mutableStateOf("") }

    val authToken = "Bearer 1413|cqWtDnePqhfUBzwa0HfxAbXdHH9S1ZJbfoJ6iLWu"
    val apiService = CountryApiSerivceInstance.apiService

    Column(modifier.padding(16.dp)) {
        TextField(
            value = countryName,
            onValueChange = { countryName = it },
            label = { Text("Type in country name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Kjør API-kallet når knappen trykkes
                if (countryName.isNotEmpty()) {
                    apiService.getCountryDetails(authToken, countryName).enqueue(object : Callback<ApiResponse> {
                        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                            if (response.isSuccessful) {
                                country = response.body()?.country
                                errorMessage = ""
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
                } else {
                    errorMessage = "Please type in a country name."
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Fetch information")
        }

        Spacer(modifier = Modifier.height(16.dp))

        country?.let {
            Text(text = "Name: ${it.name}")
            Text(text = "Full name: ${it.fullName}")
            Text(text = "Capital: ${it.capital}")
            Text(text = "President: ${it.president?.name ?: "No president found"}")
        } ?: run {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            } else {
                Text(text = "No data available.")
            }
        }
    }
}
