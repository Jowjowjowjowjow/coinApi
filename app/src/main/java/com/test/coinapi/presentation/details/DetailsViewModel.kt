package com.test.coinapi.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.domain.model.Exchange
import com.test.coinapi.domain.usecase.GetExchangesIconsUseCase
import com.test.coinapi.domain.usecase.GetFilteredByIdExchangeListUseCase
import com.test.coinapi.mapper.ApiErrorMapper.getErrorMessage
import com.test.coinapi.mapper.ExchangeMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getFilteredByIdExchangeListUseCase: GetFilteredByIdExchangeListUseCase,
    private val getExchangesIconsUseCase: GetExchangesIconsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _exchanges = MutableStateFlow<List<Exchange>>(listOf())
    val exchanges: StateFlow<List<Exchange>> = _exchanges.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, error ->
        _errorMessage.tryEmit(getErrorMessage(error))
    }

    private val _iconUrl = MutableStateFlow<String?>(null)
    val iconUrl: StateFlow<String?> = _iconUrl.asStateFlow()

    fun getExchangeListById(exchangeId: String?) =
        viewModelScope.launch(dispatcher + coroutineExceptionHandler) {
            val result = getFilteredByIdExchangeListUseCase
            if (exchangeId != null) {
                result.invoke(exchangeId = exchangeId).onSuccess {
                    _exchanges.tryEmit(ExchangeMapper.transformToList(it as List<ExchangeResponse>))
                }.onFailure { error ->
                    _errorMessage.tryEmit(getErrorMessage(error))
                }
            }
        }

    fun getExchangesIcons(exchangeId: String?) =
        viewModelScope.launch(dispatcher + coroutineExceptionHandler) {
            if (exchangeId != null) {
                val result = getExchangesIconsUseCase
                result.invoke().onSuccess { iconsList ->
                    val exchangeIcon = iconsList?.firstOrNull { it.exchangeId == exchangeId }
                    _iconUrl.tryEmit(exchangeIcon?.url)
                }.onFailure {
                    Log.e("DetailsViewModel", "getExchangesIconsError: ${it.message}")
                }
            }
        }
}