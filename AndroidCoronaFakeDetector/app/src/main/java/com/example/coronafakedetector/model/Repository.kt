package com.example.coronafakedetector.model

import com.example.coronafakedetector.model.data.Check

interface Repository {

    suspend fun checkText(text: String): Check?

    suspend fun checkImage(imageBase64: String): Check?

    suspend fun checkUrl(url: String): Check?

}
