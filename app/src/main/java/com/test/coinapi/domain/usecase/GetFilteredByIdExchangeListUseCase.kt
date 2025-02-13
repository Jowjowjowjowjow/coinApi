package com.test.coinapi.domain.usecase

import com.google.gson.Gson
import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.domain.repository.ExchangesRepository

class GetFilteredByIdExchangeListUseCase(
    private val exchangesRepository: ExchangesRepository,
    private val gson: Gson
) {

    suspend operator fun invoke(exchangeId: String): Result<List<ExchangeResponse>?> {
        val exchangeListResponse = exchangesRepository.getExchangesById(exchangeId = exchangeId)

        return if (exchangeListResponse.isSuccessful) {
            Result.success(exchangeListResponse.body())
        } else {
            val error =
                gson.fromJson(exchangeListResponse.errorBody()?.charStream(), Error::class.java)
            Result.failure(Exception(error.message))
        }
    }
}