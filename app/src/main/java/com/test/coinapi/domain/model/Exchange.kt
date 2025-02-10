package com.test.coinapi.domain.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import java.math.BigDecimal
import java.util.Date


data class Exchange(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("data_start") val dataStart: Date?,
    @SerializedName("data_end") val dataEnd: Date?,
    @SerializedName("data_quote_start") val dataQuoteStart: Date?,
    @SerializedName("data_quote_end") val dataQuoteEnd: Date?,
    @SerializedName("data_orderbook_start") val dataOrderbookStart: Date?,
    @SerializedName("data_orderbook_end") val dataOrderbookEnd: Date?,
    @SerializedName("data_trade_start") val dataTradeStart: Date?,
    @SerializedName("data_trade_end") val dataTradeEnd: Date?,
    @SerializedName("data_trade_count") val dataTradeCount: Int?,
    @SerializedName("data_symbols_count") val dataSymbolsCount: Int?,
    @SerializedName("volume_1hrs_usd") val volume1HrsUsd: BigDecimal?,
    @SerializedName("volume_1day_usd") val volume1DayUsd: BigDecimal?,
    @SerializedName("volume_1mth_usd") val volume1MthUsd: BigDecimal?,
    @SerializedName("metric_id") val metricId: List<String>?,
    @SerializedName("icons") val icons: List<Icon>?,
    @SerializedName("rank") val rank: Double
)

data class Icon(
    @SerialName("exchange_id") val exchangeId: String?,
    @SerialName("asset_id") val assetId: String?,
    @SerialName("url") val url: String?
)
