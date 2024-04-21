package com.example.service

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.example.service.ui.theme.ServiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ServiceTheme {
                // A surface container using the 'background' color from the theme
                checkPostNotifications()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context= LocalContext.current
                    StartService(this)

                }
            }
        }
    }
    private fun checkPostNotifications(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }
}

@Composable
fun StartService(context: Context){
    Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally) {
        val serviceIntent= Intent(context,TestService::class.java)

        Button(onClick = {
            context.startService(serviceIntent)


        }) {
            Text(text = "Start Service")

        }
        Button(onClick = {
            serviceIntent.action= STOP_SERVICE
            context.startService(serviceIntent)


        }) {
            Text(text = "Stop Service")

        }
    }
}