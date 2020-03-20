package com.example.coronafakedetector.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.coronafakedetector.R
import com.example.coronafakedetector.Util
import com.example.coronafakedetector.model.Repository
import com.example.coronafakedetector.model.RepositoryImpl
import com.example.coronafakedetector.network.NetworkImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope {

    private val repository: Repository = RepositoryImpl(NetworkImpl(this))
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

        setContentView(R.layout.activity_main)

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent) // Handle text being sent
                } else if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Handle single image being sent
                }
            }
            else -> {
                // Handle other intents, such as being started from the home screen
                // access when normal started
            }
        }
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
            // Update UI to reflect text being shared
            showText(text)
            checkText(text)
        }
    }

    private fun checkText(text: String) {
        launch {
            repository.checkText(text)
        }
    }

    private fun showText(text: String) {
        textView.text = text
        imageView.visibility = View.GONE
        textView.visibility = View.VISIBLE
    }

    private fun handleSendImage(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { imageUri ->
            // Update UI to reflect imageUri being shared
            showImage(imageUri)
            checkImage(imageUri)
        }
    }

    private fun checkImage(imageUri: Uri) {
        launch {
            repository.checkImage(Util.encodeToBase64(contentResolver, imageUri))
        }
    }

    private fun showImage(imageUri: Uri) {
        imageView.setImageURI(imageUri)
        textView.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

}
