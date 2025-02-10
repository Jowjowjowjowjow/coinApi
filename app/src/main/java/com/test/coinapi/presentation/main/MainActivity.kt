package com.test.coinapi.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.test.coinapi.presentation.theme.CoinApiTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: ExchangeViewModel by lazy { getViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(Unit, block = {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.exchanges.collect { exchangeList ->
                        exchangeList.forEach {
                            Log.d("Exchange: ", it.toString())
                        }
                    }
                }

                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.error.collectLatest { error ->
                        error.let {
                            Log.d("Error: ", error.toString())
                        }
                    }
                }

                viewModel.getExchangeList()
            })

            val exchangesState by viewModel.exchanges.collectAsStateWithLifecycle()
            exchangesState.forEach {
                Log.d("Exchange: ", it.toString())
            }

            val errorState by viewModel.error.collectAsStateWithLifecycle()
            errorState.let {
                Log.d("Error: ", it.toString())
            }
            CoinApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
                    LazyColumn(modifier = Modifier.padding(padding),
                        verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(
                            items = exchangesState
                        ) { exchange ->
                            Row(modifier = Modifier.background(MaterialTheme.colorScheme.outline)
                                .padding(all = 1.dp)
                                .background(MaterialTheme.colorScheme.background)
                                .fillParentMaxWidth(0.9f)) {
                                Column {
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