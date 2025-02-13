package com.test.coinapi.domain.repository

import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.data.model.IconResponse
import retrofit2.Response

interface ExchangesRepository{
    suspend fun getExchanges(): Response<List<ExchangeResponse>>
    suspend fun getExchangesById(exchangeId: String): Response<List<ExchangeResponse>>
    suspend fun getExchangesIcons(): Response<List<IconResponse>>
}
