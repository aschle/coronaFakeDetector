package com.example.coronafakedetector.model.data

data class Response(
    val author: String,
    val parent: Parent,
    val requests: Int,
    val fakeProbability: Double
)