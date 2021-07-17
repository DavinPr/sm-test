package com.suitmedia.suitmediatest.utils

import android.annotation.SuppressLint
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object FormatHelper {
    fun loadImage(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(
            imageName,
            "drawable",
            context.packageName
        )
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormat(date: String): String {
        val currentDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("en", "US"))
        val newDateFormat = SimpleDateFormat("dd MMM yyyy", Locale("en", "US"))
        val currentDate = currentDateFormat.parse(date)
        return newDateFormat.format(currentDate!!)
    }
}