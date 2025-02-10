package com.test.coinapi.mapper

import com.test.coinapi.data.model.IconResponse
import com.test.coinapi.domain.model.Icon

object IconMapper {

    fun transformFrom(source: Icon): IconResponse {
        return IconResponse(
            exchangeId = source.exchangeId,
            assetId = source.assetId,
            url = source.url
        )
    }

    fun transformTo(source: IconResponse): Icon {
        return Icon(
            exchangeId = source.exchangeId,
            assetId = source.assetId,
            url = source.url
        )
    }

    fun transformFromList(source: List<Icon>): List<IconResponse> {
        return source.map {
            transformFrom(it)
        }
    }

    fun transformToList(source: List<IconResponse>): List<Icon> {
        return source.map {
            transformTo(it)
        }
    }
}