package com.test.coinapi.mapper

import java.net.UnknownHostException

object ApiErrorMapper {
    fun getErrorMessage(error: Throwable): String {
        return when (error) {
            is UnknownHostException -> "Verifique sua conexão com a internet e tente novamente."
            else -> error.message ?: "Erro desconhecido."
        }
    }
}