package com.example.androidcoronafakedetector

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        }
    }

    private fun showImage(imageUri: Uri) {
        imageView.setImageURI(imageUri)
        textView.visibility = View.GONE
        imageView.visibility = View.VISIBLE
    }

}
