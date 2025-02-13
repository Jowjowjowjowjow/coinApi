package com.test.coinapi.data.source

import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.data.model.IconResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangesDataSource {
    @GET("exchanges")
    suspend fun getExchanges(): Response<List<ExchangeResponse>>

    @GET("exchanges/{exchangeId}")
    suspend fun getExchangesById(@Path("exchangeId") exchangeId: String): Response<List<ExchangeResponse>>

    @GET("exchanges/icons/1")
    suspend fun getExchangesIcons(): Response<List<IconResponse>>
}