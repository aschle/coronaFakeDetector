package com.example.coronafakedetector.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkImpl(private val context: Context) : Network {

    private val serverUrl = "https://coronafakedetector.herokuapp.com/api/"
    //private val serverUrl = "localhost:8080/"
    private val checkTextUrl = serverUrl + "checkText"
    private val checkImageUrl = serverUrl + "checkPhoto"
    private val checkUrlUrl = serverUrl + "checkURL?URL=%s"

    override suspend fun checkText(text: String): JSONObject? {
        return check(checkTextUrl, JSONObject().put("text", text))
    }

    override suspend fun checkImage(imageBase64: String): JSONObject? {
        return check(checkImageUrl, JSONObject().put("photo", imageBase64))
    }

    override suspend fun checkUrl(url: String): JSONObject? {
        return check(checkUrlUrl.format(url), null)
    }

    private suspend fun check(url: String, jsonObject: JSONObject?) =
        suspendCoroutine<JSONObject?> { continuation ->
            // Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(context)

            // Request a json object response from the provided URL.
            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, jsonObject,
                Response.Listener { response ->
                    continuation.resume(response)
                },
                Response.ErrorListener { error ->
                    val message =
                        if (error.localizedMessage != null) error.localizedMessage else error.toString()
                    Log.e(NetworkImpl::class.qualifiedName, message)
                    continuation.resume(null)
                }
            )

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest)
        }

}