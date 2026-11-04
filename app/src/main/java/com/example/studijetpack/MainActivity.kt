package com.example.studijetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.studijetpack.app.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Phải gọi dòng này để Android 12+ không bị crash khi dùng Theme.SplashScreen
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppNavigation()
        }
    }
}
