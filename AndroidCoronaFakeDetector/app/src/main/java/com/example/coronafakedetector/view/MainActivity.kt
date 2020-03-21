package com.example.coronafakedetector.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatActivity
import com.example.coronafakedetector.R
import com.example.coronafakedetector.Util
import com.example.coronafakedetector.model.data.Check
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
    private var sentIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        job = Job()

        setContentView(R.layout.activity_main)

        checkButton.setOnClickListener {
            checkIntent()
        }

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                sentIntent = intent
                showIntent()
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }
    }

    private fun checkIntent() {
        if ("text/plain" == sentIntent?.type) {
            sentIntent?.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                if (URLUtil.isValidUrl(text)) { // url
                    checkUrl(text)
                } else { // text
                    checkText(text)
                }
            }
        } else if (sentIntent?.type?.startsWith("image/") == true) {
            (sentIntent?.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { imageUri ->
                checkImage(imageUri)
            }
        }
    }

    // call repository

    private fun checkText(text: String) {
        launch {
            showCheckResult(repository.checkText(text))
        }
    }

    private fun checkImage(imageUri: Uri) {
        launch {
            showCheckResult(repository.checkImage(Util.encodeToBase64(contentResolver, imageUri)))
        }
    }

    private fun checkUrl(url: String) {
        launch {
            showCheckResult(repository.checkUrl(url))
        }
    }

    // UI output

    private fun showIntent() {
        if ("text/plain" == sentIntent?.type) {
            sentIntent?.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                showText(text)
            }
        } else if (sentIntent?.type?.startsWith("image/") == true) {
            (sentIntent?.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { imageUri ->
                showImage(imageUri)
            }
        }
    }

    private fun showText(text: String) {
        textView.text = text
        imageView.visibility = View.GONE
        textView.visibility = View.VISIBLE
    }

    private fun showImage(imageUri: Uri) {
        imageView.setImageURI(imageUri)
        textView.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

    private fun showCheckResult(check: Check?) {
        if (check == null) {
            showText(getString(R.string.error))
        } else {
            showText(check.response.fakeProbability.toString())
        }
    }

}
