package com.test.coinapi.domain.model

import kotlin.Error

data class Error(
    private val code: Int,
    private val message: String
)
