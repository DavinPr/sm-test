package com.suitmedia.suitmediatest.utils

import android.content.Context
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

fun Int.isPrime(): String {
    if (this == 1) return "month is not prime"
    for (i in 2 until this) {
        if (this % i == 0) return "month is not prime"
    }
    return "month is prime"
}

fun String.isPalindrome(): String {
    val noSpaceText = this.replace("\\s".toRegex(), "")

    var start = 0
    var end = noSpaceText.length - 1
    while (start < end) {
        if (noSpaceText[start] != noSpaceText[end]) {
            return "$this is not palindrome"
        }
        start++
        end--
    }
    return "$this is palindrome"
}
