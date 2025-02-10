package com.test.coinapi.data.source

import com.test.coinapi.data.model.ExchangeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExchangesDataSource {
    @GET("exchanges")
    suspend fun getExchanges(): Response<List<ExchangeResponse>>
}