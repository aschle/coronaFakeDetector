package com.example.coronafakedetector.network

import org.json.JSONObject

interface Network {

    suspend fun checkText(text: String): JSONObject

    suspend fun checkImage(imageBase64: String): JSONObject

}