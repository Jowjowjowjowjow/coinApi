package com.test.coinapi.domain.usecase

import com.google.gson.Gson
import com.test.coinapi.data.model.ExchangeResponse
import com.test.coinapi.domain.repository.ExchangesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.Reader
import java.math.BigDecimal
import kotlin.test.assertEquals

class GetFilteredByIdExchangeListUseCaseTest {

    @MockK
    private lateinit var exchangesRepository: ExchangesRepository

    @MockK
    private lateinit var gson: Gson

    private lateinit var useCaseTest: GetFilteredByIdExchangeListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCaseTest = GetFilteredByIdExchangeListUseCase(
            exchangesRepository,
            gson
        )
    }

    @Test
    fun `should get filtered by id exchange list when execute the use case`() = runBlocking {
        val exchangeResponseListMock = listOf(
            ExchangeResponse(
                exchangeId = "MERCADOBITCOIN",
                name = "Mercado Bitcoin",
                volume1DayUsd = BigDecimal.valueOf(123123123.0),
                rank = 1.0,
                website = null,
                dataStart = null,
                dataEnd = null,
                dataQuoteStart = null,
                dataQuoteEnd = null,
                dataOrderbookStart = null,
                dataOrderbookEnd = null,
                dataTradeStart = null,
                dataTradeEnd = null,
                dataTradeCount = null,
                dataSymbolsCount = null,
                volume1HrsUsd = null,
                volume1MthUsd = null,
                metricId = null,
                icons = null,
            )
        )

        val isSuccessResponseMock = true

        coEvery { exchangesRepository.getExchangesById(any()) } returns Response.success(
            exchangeResponseListMock
        )

        val result = useCaseTest.invoke("MERCADOBITCOIN")

        assertEquals(result.getOrNull(), exchangeResponseListMock)
        assertEquals(result.isSuccess, isSuccessResponseMock)
        coVerify { exchangesRepository.getExchangesById(any()) }
        confirmVerified(exchangesRepository)
    }

    @Test
    fun `should get failed response when execute the use case`() = runBlocking {
        val codeMock = 404
        val messageMock =
            "{ \"message\": \"Not found\", \"code\": 404 }".toResponseBody("application/json".toMediaTypeOrNull())
        val responseMock = "Not found"

        coEvery { exchangesRepository.getExchangesById(any()) } returns Response.error(
            codeMock,
            messageMock
        )

        every {
            gson.fromJson(
                any<Reader>(),
                Error::class.java
            )
        } returns Error(responseMock)

        val result = useCaseTest.invoke("MERCADOBITCOIN")

        assertEquals(result.isFailure, true)
        assertEquals(responseMock, result.exceptionOrNull()?.message)
        coVerify { exchangesRepository.getExchangesById(any()) }
        confirmVerified(exchangesRepository)
    }
}