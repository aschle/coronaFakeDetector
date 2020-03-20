package com.example.coronafakedetector.model

import com.example.coronafakedetector.network.Network

class RepositoryImpl(private val network: Network) : Repository {

    private var mapHelper: MapHelper = MapHelper()

    override suspend fun checkText(text: String): Check {
        val checkJSON = network.checkText(text)
        return mapHelper.map(checkJSON)
    }

    override suspend fun checkImage(imageBase64: String): Check {
        val checkJSON = network.checkImage(imageBase64)
        return mapHelper.map(checkJSON)
    }

}