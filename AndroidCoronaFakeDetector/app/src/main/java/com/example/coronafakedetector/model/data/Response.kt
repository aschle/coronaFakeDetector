package com.example.coronafakedetector.model.data

data class Response(
    val author: String,
    val parent: Parent,
    val requests: Int,
    val fakeProbability: Double
) {
    override fun toString(): String {
        return "Author: $author\nParent: $parent\nRequests: $requests\n"
    }
}