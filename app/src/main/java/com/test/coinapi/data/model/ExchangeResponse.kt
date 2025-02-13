package com.test.coinapi.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ExchangeResponse(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("data_start") val dataStart: String?,
    @SerializedName("data_end") val dataEnd: String?,
    @SerializedName("data_quote_start") val dataQuoteStart: String?,
    @SerializedName("data_quote_end") val dataQuoteEnd: String?,
    @SerializedName("data_orderbook_start") val dataOrderbookStart: String?,
    @SerializedName("data_orderbook_end") val dataOrderbookEnd: String?,
    @SerializedName("data_trade_start") val dataTradeStart: String?,
    @SerializedName("data_trade_end") val dataTradeEnd: String?,
    @SerializedName("data_trade_count") val dataTradeCount: Int?,
    @SerializedName("data_symbols_count") val dataSymbolsCount: Int?,
    @SerializedName("volume_1hrs_usd") val volume1HrsUsd: BigDecimal?,
    @SerializedName("volume_1day_usd") val volume1DayUsd: BigDecimal?,
    @SerializedName("volume_1mth_usd") val volume1MthUsd: BigDecimal?,
    @SerializedName("metric_id") val metricId: List<String>?,
    @SerializedName("icons") val icons: List<IconResponse>?,
    @SerializedName("rank") val rank: Double
)

data class IconResponse(
    @SerializedName("exchange_id") val exchangeId: String?,
    @SerializedName("asset_id") val assetId: String?,
    @SerializedName("url") val url: String?
)