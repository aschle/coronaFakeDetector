package com.example.coronafakedetector.model

import org.json.JSONObject

class MapHelper {

    fun map(jsonObject: JSONObject): Check {
        return Check(
            id = jsonObject.getInt("id"),
            rating = jsonObject.getDouble("rating"),
            description = jsonObject.getString("description")
        )
    }

}
