package com.suitmedia.suitmediatest.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.suitmedia.suitmediatest.ui.home.choice.ChoiceFragment
import java.text.SimpleDateFormat
import java.util.*

fun Context.loadImage(imageName: String): Int {
    return resources.getIdentifier(
        imageName,
        "drawable",
        packageName
    )
}

fun String.dateFormat(): String {
    val currentDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale("en", "US"))
    val newDateFormat = SimpleDateFormat("dd MMM yyyy", Locale("en", "US"))
    val currentDate = currentDateFormat.parse(this)
    return newDateFormat.format(currentDate!!)
}

fun Int.fizzBuzzByDate(): String {
    return if (this % 2 == 0 && this % 3 == 0) {
        "iOS"
    } else if (this % 2 == 0) {
        "Blackberry"
    } else if (this % 3 == 0) {
        "Android"
    } else {
        "Feature Phone"
    }
}
