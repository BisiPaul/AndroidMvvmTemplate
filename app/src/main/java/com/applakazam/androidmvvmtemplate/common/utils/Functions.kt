package com.applakazam.androidmvvmtemplate.common.utils

import android.R.attr.text
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect


/**
 *  Created by paulbisioc on 23.05.2023
 */
object Functions {

    fun createBitmapOfInitials(initials: String): Bitmap {
        val bitmapWidth = 200
        val bitmapHeight = 200

        val bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap).apply {
            drawColor(Color.BLUE)
        }

        val paint = Paint().apply {
            color = Color.WHITE
            textSize = 50f
            isAntiAlias = true
        }

        val boundsText = Rect()
        paint.getTextBounds(initials, 0, initials.length, boundsText)
        val x = (bitmap.width - boundsText.width()) / 2
        val y = (bitmap.height + boundsText.height()) / 2

        canvas.drawText(initials, x.toFloat(), y.toFloat(), paint)

        return bitmap
    }
}