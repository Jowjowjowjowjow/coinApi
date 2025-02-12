package com.test.coinapi.presentation.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavHostController
import com.test.coinapi.R

@Composable
fun DetailsScreen(
    navController: NavHostController,
    exchangeId: String?
) {
    val context = LocalContext.current

    DetailsContent(
        topBarTitle = exchangeId
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    topBarTitle: String?
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                if (topBarTitle != null) {
                    Text(text = topBarTitle)
                }
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray)
        )
    }) { padding ->
        Text("AEEEEEEEEEEEEEE", modifier = Modifier.padding(padding))
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsContent(
        topBarTitle = "CoinApi"
    )
}