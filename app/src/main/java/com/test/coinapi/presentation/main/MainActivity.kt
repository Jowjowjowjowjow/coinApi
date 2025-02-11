package com.test.coinapi.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.coinapi.R
import com.test.coinapi.presentation.theme.CoinApiTheme
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ExchangeViewModel by lazy { getViewModel() }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit, block = {
                viewModel.getExchangeList()
            })

            val exchangesState by viewModel.exchanges.collectAsStateWithLifecycle()

            val errorState by viewModel.error.collectAsStateWithLifecycle()

            CoinApiTheme {
                Scaffold(topBar = {
                    TopAppBar(
                        title = { Text(text = getString(R.string.app_name)) },
                        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray)
                    )
                }) { padding ->
                    if (exchangesState.isEmpty() && errorState == null){
                        CircularProgressIndicator()
                    }
                    if (errorState == null) {
                        LazyColumn(
                            modifier = Modifier.padding(padding),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(
                                items = exchangesState
                            ) { exchange ->
                                Row(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.background)
                                        .fillParentMaxWidth()
                                        .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                                ) {
                                    Column(modifier = Modifier.padding(start = 4.dp)) {
                                        exchange.name?.let {
                                            Text(
                                                it,
                                                modifier = Modifier.padding(bottom = 2.dp)
                                            )
                                        }
                                        exchange.exchangeId?.let {
                                            Text(
                                                it,
                                                modifier = Modifier.padding(bottom = 2.dp)
                                            )
                                        }
                                        exchange.volume1DayUsd?.let {
                                            Text(
                                                "US$ $it",
                                                modifier = Modifier.padding(bottom = 2.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxWidth(1f)
                        ) {
                            errorState?.message?.let {
                                Text(
                                    text = it
                                )
                            }
                            Button(onClick = { viewModel.getExchangeList() }) {
                                Text("Tentar novamente")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainPreview() {
    CoinApiTheme {

    }
}