package com.agents.valorantagents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.agents.main.presentation.core.MainScreen
import com.agents.valorantagents.theme.ValorantAgentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            ValorantAgentsTheme {
                MainScreen()
            }
        }
    }
}
