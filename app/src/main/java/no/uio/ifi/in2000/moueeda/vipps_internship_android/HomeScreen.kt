package no.uio.ifi.in2000.moueeda.vipps_internship_android

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import no.uio.ifi.in2000.moueeda.vipps_internship_android.Model.Country
import no.uio.ifi.in2000.moueeda.vipps_internship_android.ui.theme.VippsinternshipandroidTheme
import androidx.compose.foundation.layout.Column
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CountryDetailsScreen(modifier: Modifier = Modifier) {
    var country by remember { mutableStateOf<Country?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    val apiService = CountryApiSerivceInstance.apiService
    val authToken = "Bearer 1413|cqWtDnePqhfUBzwa0HfxAbXdHH9S1ZJbfoJ6iLWu"

    LaunchedEffect(Unit) {
        apiService.getCountryDetails(authToken).enqueue(object : Callback<Country> {
            override fun onResponse(call: Call<Country>, response: Response<Country>) {
                if (response.isSuccessful) {
                    country = response.body()
                } else {
                    errorMessage = "Response not successful"
                }
            }

            override fun onFailure(call: Call<Country>, t: Throwable) {
                errorMessage = t.message.toString()
            }
        })
    }

    Column(modifier) {
        country?.let {
            Text(text = "Name: ${it.name}")
            Text(text = "Capital: ${it.capital}")
            Text(text = "President: ${it.president}")
        } ?: run {
            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage)
            } else {
                Text(text = "Loading...")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VippsinternshipandroidTheme {
        Scaffold {
            CountryDetailsScreen(modifier = Modifier.padding(it))
        }
    }
}