package com.test.coinapi.domain.usecase

import com.google.gson.Gson
import com.test.coinapi.data.model.IconResponse
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
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.Reader

class GetExchangesIconsUseCaseTest {

    @MockK
    private lateinit var exchangesRepository: ExchangesRepository

    @MockK
    private lateinit var gson: Gson

    private lateinit var useCaseTest: GetExchangesIconsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCaseTest = GetExchangesIconsUseCase(
            exchangesRepository,
            gson
        )
    }

    @Test
    fun `should get exchange icons list when execute the use case`() = runBlocking {
        val iconResponseListMock = listOf(
            IconResponse(
                exchangeId = "MERCADOBITCOIN",
                assetId = null,
                url = "https://i.ytimg.com/vi/dQw4w9WgXcQ/sddefault.jpg"
            )
        )

        val isSuccessResponseMock = true

        coEvery { exchangesRepository.getExchangesIcons() } returns Response.success(
            iconResponseListMock
        )

        val result = useCaseTest.invoke()

        assertEquals(result.getOrNull(), iconResponseListMock)
        assertEquals(result.isSuccess, isSuccessResponseMock)
        coVerify { exchangesRepository.getExchangesIcons() }
        confirmVerified(exchangesRepository)
    }

    @Test
    fun `should get failed response when execute the use case`() = runBlocking {
        val codeMock = 404
        val messageMock =
            "{ \"message\": \"Not found\", \"code\": 404 }".toResponseBody("application/json".toMediaTypeOrNull())
        val responseMock = "Not found"

        coEvery { exchangesRepository.getExchangesIcons() } returns Response.error(
            codeMock,
            messageMock
        )

        every {
            gson.fromJson(
                any<Reader>(),
                Error::class.java
            )
        } returns Error(responseMock)

        val result = useCaseTest.invoke()

        assertEquals(result.isFailure, true)
        assertEquals(responseMock, result.exceptionOrNull()?.message)
        coVerify { exchangesRepository.getExchangesIcons() }
        confirmVerified(exchangesRepository)
    }
}