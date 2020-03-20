package com.example.coronafakedetector.model

interface Repository {

    suspend fun checkText(text: String): Check

    suspend fun checkImage(imageBase64: String): Check

}
