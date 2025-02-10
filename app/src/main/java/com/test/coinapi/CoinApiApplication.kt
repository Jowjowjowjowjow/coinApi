package com.test.coinapi

import android.app.Application
import com.test.coinapi.di.coinApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoinApiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinApiApplication)
            modules(coinApiModule)
        }
    }
}