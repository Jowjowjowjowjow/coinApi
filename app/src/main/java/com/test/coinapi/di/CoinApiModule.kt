package com.test.coinapi.di

import com.google.gson.Gson
import com.test.coinapi.BuildConfig
import com.test.coinapi.data.repository.ExchangesRepositoryImpl
import com.test.coinapi.data.source.ExchangesDataSource
import com.test.coinapi.domain.repository.ExchangesRepository
import com.test.coinapi.domain.usecase.GetExchangeListUseCase
import com.test.coinapi.domain.usecase.GetExchangesIconsUseCase
import com.test.coinapi.domain.usecase.GetFilteredByIdExchangeListUseCase
import com.test.coinapi.presentation.details.DetailsViewModel
import com.test.coinapi.presentation.main.MainScreenViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = "https://rest.coinapi.io/v1/"

private val AUTH_INTERCEPTOR = named("AUTH_INTERCEPTOR")

val coinApiModule = module {

    single<Interceptor>(AUTH_INTERCEPTOR){
        Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Content-Type", "application/json")
            builder.header("X-CoinAPI-Key", BuildConfig.COIN_API_TOKEN)
            chain.proceed(builder.build())
        }
    }
    single<OkHttpClient> {
        val builder = OkHttpClient.Builder().apply {
            readTimeout(2,TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES)
            addInterceptor(get<Interceptor>(AUTH_INTERCEPTOR))
        }
        builder.build()
    }

    single<Retrofit> {
        val converter = GsonConverterFactory.create()

        Retrofit.Builder()
            .client(get())
            .addConverterFactory(converter)
            .baseUrl(baseUrl)
            .build()
    }

    single<Gson> {
        Gson()
    }

    single<ExchangesDataSource> {
        get<Retrofit>().create(ExchangesDataSource::class.java)
    }

    single<ExchangesRepository> {
        ExchangesRepositoryImpl(
            exchangesDataSource = get()
        )
    }

    single<GetExchangeListUseCase> {
        GetExchangeListUseCase(
            exchangesRepository = get(),
            gson = get()
        )
    }

    single<GetFilteredByIdExchangeListUseCase>{
        GetFilteredByIdExchangeListUseCase(
            exchangesRepository = get(),
            gson = get()
        )
    }

    viewModel {
        MainScreenViewModel(
            getExchangeListUseCase = get()
        )
    }

    single<GetExchangesIconsUseCase>{
        GetExchangesIconsUseCase(
            exchangesRepository = get(),
            gson = get()
        )
    }

    viewModel {
        DetailsViewModel(
            getFilteredByIdExchangeListUseCase = get(),
            getExchangesIconsUseCase = get()
        )
    }

}