package com.example.coronafakedetector.model

import com.example.coronafakedetector.model.data.Check
import com.example.coronafakedetector.model.data.Parent
import com.example.coronafakedetector.model.data.Response
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MapHelper {

    fun map(jsonObject: JSONObject?): Check? {
        if (jsonObject == null) {
            return null
        }
        val responseJSONObject = jsonObject.getJSONObject("response")
        val parentJSONObject = responseJSONObject.getJSONObject("parent")
        return Check(
            timestamp = LocalDateTime.parse(
                jsonObject.getString("timestamp"),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME
            ),
            response = Response(
                author = responseJSONObject.getString("author"),
                parent = Parent(
                    url = parentJSONObject.getString("url")
                ),
                requests = responseJSONObject.getInt("requests"),
                fakeProbability = responseJSONObject.getDouble("fakeProbability")
            )
        )
    }

}
