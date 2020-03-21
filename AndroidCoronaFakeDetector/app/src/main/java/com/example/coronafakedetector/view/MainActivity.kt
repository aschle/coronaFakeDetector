package com.example.coronafakedetector.view

import android.content.Intent
import android.graphics.Color
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
import com.example.coronafakedetector.model.data.Parent
import com.example.coronafakedetector.model.data.Response
import com.example.coronafakedetector.network.NetworkImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.time.LocalDateTime
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

        checkButton.setOnClickListener {
            checkIntent()
        }

        when (intent?.action) {
            Intent.ACTION_SEND -> {
                checkButton.isEnabled = true
                showIntent()
            }
            else -> {
                // Handle other intents, such as being started from the home screen
            }
        }
    }

    // call repository

    private fun checkIntent() {
        if ("text/plain" == intent.type) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                showProgress()
                if (URLUtil.isValidUrl(text)) { // url
                    checkUrl(text)
                } else { // text
                    checkText(text)
                }
            }
        } else if (intent.type?.startsWith("image/") == true) {
            (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { imageUri ->
                checkImage(imageUri)
            }
        }
    }

    private fun checkText(text: String) {
        launch {
            showProgress()
            showCheckResult(repository.checkText(text))
            unshowProgress()
        }
    }

    private fun checkImage(imageUri: Uri) {
        launch {
            showProgress()
            showCheckResult(repository.checkImage(Util.encodeToBase64(contentResolver, imageUri)))
            unshowProgress()
        }
    }

    private fun checkUrl(url: String) {
        launch {
            showProgress()
            showCheckResult(repository.checkUrl(url))
            unshowProgress()
        }
    }

    // UI output

    private fun showProgress() {
        textView.visibility = View.GONE
        imageView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun unshowProgress() {
        progressBar.visibility = View.GONE
    }

    private fun showIntent() {
        textViewProbability.visibility = View.GONE
        if ("text/plain" == intent.type) {
            intent.getStringExtra(Intent.EXTRA_TEXT)?.let { text ->
                showInputText(text)
            }
        } else if (intent.type?.startsWith("image/") == true) {
            (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let { imageUri ->
                showInputImage(imageUri)
            }
        }
    }

    private fun showInputText(text: String) {
        header.text = getString(R.string.input)
        showText(text)
    }

    private fun showInputImage(imageUri: Uri) {
        header.text = getString(R.string.input)
        imageView.setImageURI(imageUri)
        textView.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

    private fun showOutputText(check: Check) {
        header.text = getString(R.string.output)
        showProbability(check.response.fakeProbability)
        showText(check.toString())
    }

    private fun showProbability(probability: Double) {
        textViewProbability.visibility = View.VISIBLE
        textViewProbability.text = getString(R.string.fake_probability).format(probability.toString())
        when {
            probability > 66.0 -> {
                textViewProbability.setTextColor(Color.RED)
            }
            probability > 33.0 -> {
                textViewProbability.setTextColor(Color.BLACK)
            }
            else -> {
                textViewProbability.setTextColor(Color.GREEN)
            }
        }
    }

    private fun showText(text: String) {
        textView.text = text
        textView.visibility = View.VISIBLE
        imageView.visibility = View.GONE
    }

    private fun showCheckResult(check: Check?) {
        //val checkTmp = Check(LocalDateTime.now(), Response("Lukas", Parent("https://lukas.com"), 5, 80.0))
        if (check == null) {
            showText(getString(R.string.error))
        } else {
            showOutputText(check)
        }
    }

}
