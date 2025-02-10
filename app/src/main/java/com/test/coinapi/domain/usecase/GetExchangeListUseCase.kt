package com.test.coinapi.domain.usecase

import com.google.gson.Gson
import com.test.coinapi.domain.UseCaseResult
import com.test.coinapi.domain.repository.ExchangesRepository

class GetExchangeListUseCase (
    private val exchangesRepository: ExchangesRepository,
    private val gson: Gson
){

    //TODO: buscar uma solução melhor que esse any
    suspend operator fun invoke(): UseCaseResult<Any?> {
        val exchangeListResponse = exchangesRepository.getExchanges()
        return if (exchangeListResponse.isSuccessful) {
            UseCaseResult.Success(exchangeListResponse.body())
        } else {
            val error = gson.fromJson(exchangeListResponse.errorBody()?.charStream(), Error::class.java)
            UseCaseResult.Failure(Exception(error.message))
        }
    }
}
