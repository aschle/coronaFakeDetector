package com.example.coronafakedetector.network

import kotlinx.coroutines.delay
import org.json.JSONObject

class MockNetwork : Network {

    override suspend fun checkText(text: String): JSONObject? {
        delay(1000L)
        return JSONObject(mockResultBad())
    }

    override suspend fun checkImage(imageBase64: String): JSONObject? {
        delay(2000L)
        return JSONObject(mockResultBad())
    }

    override suspend fun checkUrl(url: String): JSONObject? {
        delay(500L)
        return JSONObject(mockResultGood())
    }

    private fun mockResultBad(): String {
        return """{
                "timestamp": "2020-03-22T11:16:22.461",
                "response": {
                    "author": "BestFaker",
                    "parent": {
                        "url": "http://www.fake-news.com"
                    },
                    "requests": 53,
                    "fakeProbability": 94.5
                }
            }"""
    }

    private fun mockResultGood(): String {
        return """{
                "timestamp": "2020-03-22T11:16:22.461",
                "response": {
                    "author": "Max Mustermann",
                    "parent": {
                        "url": "http://www.wikipedia.com"
                    },
                    "requests": 36,
                    "fakeProbability": 14.8
                }
            }"""
    }

}