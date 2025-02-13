package com.test.coinapi.domain.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.Date

data class Exchange(
    @SerializedName("exchange_id") val exchangeId: String? = null,
    @SerializedName("website") val website: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("data_start") val dataStart: Date? = null,
    @SerializedName("data_end") val dataEnd: Date? = null,
    @SerializedName("data_quote_start") val dataQuoteStart: Date? = null,
    @SerializedName("data_quote_end") val dataQuoteEnd: Date? = null,
    @SerializedName("data_orderbook_start") val dataOrderbookStart: Date? = null,
    @SerializedName("data_orderbook_end") val dataOrderbookEnd: Date? = null,
    @SerializedName("data_trade_start") val dataTradeStart: Date? = null,
    @SerializedName("data_trade_end") val dataTradeEnd: Date? = null,
    @SerializedName("data_trade_count") val dataTradeCount: Int? = null,
    @SerializedName("data_symbols_count") val dataSymbolsCount: Int? = null,
    @SerializedName("volume_1hrs_usd") val volume1HrsUsd: BigDecimal? = null,
    @SerializedName("volume_1day_usd") val volume1DayUsd: BigDecimal? = null,
    @SerializedName("volume_1mth_usd") val volume1MthUsd: BigDecimal? = null,
    @SerializedName("metric_id") val metricId: List<String>? = null,
    @SerializedName("icons") val icons: List<Icon>? = null,
    @SerializedName("rank") val rank: Double
)

data class Icon(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("asset_id") val assetId: String?,
    @SerializedName("url") val url: String?
)
