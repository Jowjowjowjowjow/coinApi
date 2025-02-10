package com.test.coinapi.mapper

import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.domain.model.Exchange
import com.test.coinapi.extensions.toDate

object ExchangeMapper {

    fun transformFrom(source: Exchange): ExchangeResponse {
        return ExchangeResponse(
            exchangeId = source.exchangeId,
            website = source.website,
            name = source.name,
            dataStart = source.dataStart?.toString(),
            dataEnd = source.dataEnd?.toString(),
            dataQuoteStart = source.dataQuoteStart?.toString(),
            dataQuoteEnd = source.dataQuoteEnd?.toString(),
            dataOrderbookStart = source.dataOrderbookStart?.toString(),
            dataOrderbookEnd = source.dataOrderbookEnd?.toString(),
            dataTradeStart = source.dataTradeStart?.toString(),
            dataTradeEnd = source.dataTradeEnd?.toString(),
            dataTradeCount = source.dataTradeCount,
            dataSymbolsCount = source.dataSymbolsCount,
            volume1HrsUsd = source.volume1HrsUsd,
            volume1DayUsd = source.volume1DayUsd,
            volume1MthUsd = source.volume1MthUsd,
            metricId = source.metricId,
            icons = source.icons?.let { IconMapper.transformFromList(it) },
            rank = source.rank
        )
    }

    fun transformTo(source: ExchangeResponse): Exchange {
        return Exchange(
            exchangeId = source.exchangeId,
            website = source.website,
            name = source.name,
            dataStart = source.dataStart?.toDate(),
            dataEnd = source.dataEnd?.toDate(),
            dataQuoteStart = source.dataQuoteStart?.toDate(),
            dataQuoteEnd = source.dataQuoteEnd?.toDate(),
            dataOrderbookStart = source.dataOrderbookStart?.toDate(),
            dataOrderbookEnd = source.dataOrderbookEnd?.toDate(),
            dataTradeStart = source.dataTradeStart?.toDate(),
            dataTradeEnd = source.dataTradeEnd?.toDate(),
            dataTradeCount = source.dataTradeCount,
            dataSymbolsCount = source.dataSymbolsCount,
            volume1HrsUsd = source.volume1HrsUsd,
            volume1DayUsd = source.volume1DayUsd,
            volume1MthUsd = source.volume1MthUsd,
            metricId = source.metricId,
            icons = source.icons?.let { IconMapper.transformToList(it) },
            rank = source.rank
        )
    }

    fun transformFromList(source: List<Exchange>): List<ExchangeResponse> {
        return source.map {
            transformFrom(it)
        }
    }

    fun transformToList(source: List<ExchangeResponse>): List<Exchange> {
        return source.map {
            transformTo(it)
        }
    }
}