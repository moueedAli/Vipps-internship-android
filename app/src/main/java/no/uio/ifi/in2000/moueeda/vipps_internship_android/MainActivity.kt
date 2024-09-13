package no.uio.ifi.in2000.moueeda.vipps_internship_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import no.uio.ifi.in2000.moueeda.vipps_internship_android.ui.theme.VippsinternshipandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VippsinternshipandroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CountryDetailsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


// token is 1413|cqWtDnePqhfUBzwa0HfxAbXdHH9S1ZJbfoJ6iLWu

