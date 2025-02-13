package com.test.coinapi.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.domain.model.Exchange
import com.test.coinapi.domain.usecase.GetExchangeListUseCase
import com.test.coinapi.mapper.ApiErrorMapper.getErrorMessage
import com.test.coinapi.mapper.ExchangeMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException


class MainScreenViewModel(
    private val getExchangeListUseCase: GetExchangeListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _exchanges = MutableStateFlow<List<Exchange>>(listOf())
    val exchanges: StateFlow<List<Exchange>> = _exchanges.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, error ->
        _errorMessage.tryEmit(getErrorMessage(error))
    }

    fun getExchangeList() = viewModelScope.launch(dispatcher + coroutineExceptionHandler) {
        val result = getExchangeListUseCase
        result.invoke().onSuccess {
            _exchanges.tryEmit(ExchangeMapper.transformToList(it as List<ExchangeResponse>))
        }.onFailure { error ->
            _errorMessage.tryEmit(getErrorMessage(error))
        }
    }
}