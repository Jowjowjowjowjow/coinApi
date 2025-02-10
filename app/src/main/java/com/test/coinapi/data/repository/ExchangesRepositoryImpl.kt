package com.test.coinapi.data.repository

import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.data.source.ExchangesDataSource
import com.test.coinapi.domain.repository.ExchangesRepository
import retrofit2.Response

class ExchangesRepositoryImpl(private val exchangesDataSource: ExchangesDataSource) :
    ExchangesRepository {
    override suspend fun getExchanges(): Response<List<ExchangeResponse>> =
        exchangesDataSource.getExchanges()
}