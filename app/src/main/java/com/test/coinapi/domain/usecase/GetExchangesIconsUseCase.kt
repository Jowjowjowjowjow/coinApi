package com.test.coinapi.domain.usecase

import com.google.gson.Gson
import com.test.coinapi.data.model.IconResponse
import com.test.coinapi.domain.repository.ExchangesRepository

class GetExchangesIconsUseCase(
    private val exchangesRepository: ExchangesRepository,
    private val gson: Gson
) {

    suspend operator fun invoke(): Result<List<IconResponse>?> {
        val exchangeListResponse = exchangesRepository.getExchangesIcons()

        return if (exchangeListResponse.isSuccessful) {
            Result.success(exchangeListResponse.body())
        } else {
            val error =
                gson.fromJson(exchangeListResponse.errorBody()?.charStream(), Error::class.java)
            Result.failure(Exception(error.message))
        }
    }
}