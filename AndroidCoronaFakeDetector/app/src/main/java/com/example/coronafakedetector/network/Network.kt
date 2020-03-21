package com.example.coronafakedetector.network

import org.json.JSONObject
import java.net.URL

interface Network {

    suspend fun checkText(text: String): JSONObject

    suspend fun checkImage(imageBase64: String): JSONObject

    suspend fun checkUrl(url: String): JSONObject

}