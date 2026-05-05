package com.dylanloyola.dailyflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dylanloyola.dailyflow.ui.main.MainScreen
import com.dylanloyola.dailyflow.ui.theme.DailyFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyFlowTheme {
                MainScreen()
            }
        }
    }
}
