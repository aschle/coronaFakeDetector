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

// TODO
class NetworkImpl(private val context: Context) : Network {

    private val TAG = NetworkImpl::class.qualifiedName

    private val url = "TODO"

    override suspend fun checkText(text: String): JSONObject {
        return loadJsonFrom(url)
    }

    override suspend fun checkImage(imageBase64: String): JSONObject {
        return loadJsonFrom(url)
    }

    private suspend fun loadJsonFrom(url: String) = suspendCoroutine<JSONObject> { continuation ->
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)

        // Request a json object response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, null,
            Response.Listener { response ->
                continuation.resume(response)
            },
            Response.ErrorListener { error ->
                val message =
                    if (error.localizedMessage != null) error.localizedMessage else error.toString()
                Log.e(TAG, message)
                continuation.resume(JSONObject())
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

}