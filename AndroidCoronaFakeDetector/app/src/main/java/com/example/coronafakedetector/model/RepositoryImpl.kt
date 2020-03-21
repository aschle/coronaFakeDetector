package com.example.coronafakedetector.model

import com.example.coronafakedetector.network.Network
import java.net.URL

class RepositoryImpl(private val network: Network) : Repository {

    private var mapHelper: MapHelper = MapHelper()

    override suspend fun checkText(text: String): Check {
        return mapHelper.map(network.checkText(text))
    }

    override suspend fun checkImage(imageBase64: String): Check {
        return mapHelper.map(network.checkImage(imageBase64))
    }

    override suspend fun checkUrl(url: String): Check {
        return mapHelper.map( network.checkUrl(url))
    }

}