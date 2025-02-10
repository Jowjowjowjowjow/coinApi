package com.test.coinapi.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.domain.model.Exchange
import com.test.coinapi.domain.usecase.GetExchangeListUseCase
import com.test.coinapi.mapper.ExchangeMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ExchangeViewModel(
    private val getExchangeListUseCase: GetExchangeListUseCase
): ViewModel() {

    private val _exchanges = MutableStateFlow<List<Exchange>>(listOf())
    val exchanges: StateFlow<List<Exchange>> = _exchanges.asStateFlow()

    private val _error = MutableStateFlow<Throwable>(Exception())
    val error: StateFlow<Throwable> = _error.asStateFlow()

    fun getExchangeList() = viewModelScope.launch(Dispatchers.IO) {
        val result = getExchangeListUseCase

        result.invoke().then(
            onSuccess = { exchangeList ->
                _exchanges.tryEmit(ExchangeMapper.transformToList(exchangeList as List<ExchangeResponse>))
            },
            onError = { error ->
                _error.tryEmit(error)
            }
        )
    }
}