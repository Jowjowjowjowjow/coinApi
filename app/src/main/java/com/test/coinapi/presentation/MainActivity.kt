package com.test.coinapi.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.test.coinapi.presentation.navigation.AppNavHost
import com.test.coinapi.presentation.theme.CoinApiTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinApiTheme {
                AppNavHost()
            }
        }
    }
}
