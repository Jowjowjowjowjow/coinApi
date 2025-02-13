package com.test.coinapi.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.test.coinapi.domain.model.Exchange
import com.test.coinapi.domain.model.Icon
import com.test.coinapi.extensions.format
import org.koin.androidx.compose.koinViewModel
import java.math.BigDecimal


@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    navController: NavHostController,
    exchangeId: String?
) {
    LaunchedEffect(Unit) {
        viewModel.getExchangeListById(exchangeId)
        viewModel.getExchangesIcons(exchangeId)
    }

    val exchangesState by viewModel.exchanges.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()
    val iconUrl by viewModel.iconUrl.collectAsStateWithLifecycle()

    if (exchangesState.isEmpty().not() || errorMessage != null) {
        DetailsContent(
            exchange = exchangesState.first(),
            errorMessage = errorMessage,
            iconUrl = iconUrl,
            hasPreviousBackStackEntry = navController.previousBackStackEntry != null,
            tryAgainClick = {
                viewModel.getExchangeListById(exchangeId)
            },
            onBackClick = {
                navController.navigateUp()
            }
        )
    } else {
        DetailsIdNotFoundContent(
           onBackClick = {
               navController.navigateUp()
           }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(
    exchange: Exchange,
    iconUrl: String?,
    errorMessage: String?,
    hasPreviousBackStackEntry: Boolean = false,
    onBackClick: () -> Unit,
    tryAgainClick: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                if (exchange.name != null) {
                    Text(text = exchange.name)
                }
            },
            navigationIcon = {
                if (hasPreviousBackStackEntry) {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                } else {
                    null
                }
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray),
            )
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colorScheme.outline)
                .padding(start = 4.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (errorMessage == null) {
                iconUrl?.let {
                    AsyncImage(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        model = it,
                        contentDescription = "Exchange icon"
                    )
                }
                exchange.name?.let {
                    Text(
                        "Name: $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.exchangeId?.let {
                    Text(
                        "Exchange ID: $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.website?.let {
                    Text(
                        "Website: $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataStart?.let {
                    Text(
                        "Data Start: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataEnd?.let {
                    Text(
                        "Data End: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataQuoteStart?.let {
                    Text(
                        "Data Quote Start: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataQuoteEnd?.let {
                    Text(
                        "Data Quote End: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataOrderbookStart?.let {
                    Text(
                        "Data Orderbook Start: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataOrderbookEnd?.let {
                    Text(
                        "Data Orderbook End: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataTradeStart?.let {
                    Text(
                        "Data Trade Start: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataTradeEnd?.let {
                    Text(
                        "Data Trade End: ${it.format()}",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataTradeCount?.let {
                    Text(
                        "Data Trade Count: $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.dataSymbolsCount?.let {
                    Text(
                        "Data Symbols Count: $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.volume1HrsUsd?.let {
                    Text(
                        "Volume 1Hr USD: US$ $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.volume1DayUsd?.let {
                    Text(
                        "Volume 1Day USD: US$ $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.volume1MthUsd?.let {
                    Text(
                        "Volume 1Mth USD: US$ $it",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
                exchange.metricId?.let { metricIdList ->
                    metricIdList.forEach {
                        Text(
                            "Metric ID: $it",
                            modifier = Modifier.padding(bottom = 2.dp)
                        )
                    }
                }
                Text(
                    "Rank: ${exchange.rank}",
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            } else {
                Text(
                    text = errorMessage,
                    modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                    textAlign = TextAlign.Center
                )
                Button(onClick = { tryAgainClick() }) {
                    //TODO: Pegar texto das strings
                    Text("Tentar novamente")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsContent(
        Exchange(
            exchangeId = "MERCADO BITCOIN",
            name = "Mercado Bitcoin",
            volume1DayUsd = BigDecimal.valueOf(123456789.0),
            rank = 1.0
        ),
        iconUrl = null,
        hasPreviousBackStackEntry = true,
        errorMessage = null,
        tryAgainClick = {},
        onBackClick = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsIdNotFoundContent(
    onBackClick: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "CoinApi Details")
            },
            scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray)
        )
    }) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(1f)
        ) {
            Text(
                //TODO: Pegar texto das strings
                text = "ExchangeId não encontrado"
            )
            Button(onClick = { onBackClick() }) {
                //TODO: Pegar texto das strings
                Text("Voltar")
            }
        }
    }
}

@Preview
@Composable
fun DetailsIdNotFoundContentPreview() {
    DetailsIdNotFoundContent(onBackClick = {})
}

