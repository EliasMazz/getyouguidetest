package br.com.getyourguide.review.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

fun String.fromBase64() : Bitmap {
    val imageAsBytes = Base64.decode(this.toByteArray(), Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
}
