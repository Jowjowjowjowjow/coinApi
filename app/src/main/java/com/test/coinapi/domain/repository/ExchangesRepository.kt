package com.test.coinapi.domain.repository

import com.test.coinapi.data.model.ExchangeResponse
import retrofit2.Response

interface ExchangesRepository{
    suspend fun getExchanges(): Response<List<ExchangeResponse>>
}
