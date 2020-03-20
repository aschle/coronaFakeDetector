package com.example.coronafakedetector

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

object Util {

    // from: https://stackoverflow.com/questions/36189503/take-picture-and-convert-to-base64
    fun encodeToBase64(contentResolver: ContentResolver, imageUri: Uri): String {
        val imageStream = contentResolver.openInputStream(imageUri)
        val imageBitmap = BitmapFactory.decodeStream(imageStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.URL_SAFE)
    }

}