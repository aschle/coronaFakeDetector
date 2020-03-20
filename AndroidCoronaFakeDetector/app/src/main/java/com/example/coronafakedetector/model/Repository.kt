package com.example.coronafakedetector.model

import java.net.URL

interface Repository {

    suspend fun checkText(text: String): Check

    suspend fun checkImage(imageBase64: String): Check

    suspend fun checkUrl(url: String): Check

}
