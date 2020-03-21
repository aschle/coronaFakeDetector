package com.example.coronafakedetector.model.data

import java.time.LocalDateTime

data class Check(
    val timestamp: LocalDateTime,
    val response: Response
) {
    override fun toString(): String {
        return response.toString()
    }
}
