package com.suitmedia.suitmediatest.ui.home.choice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChoiceViewModel : ViewModel() {
    fun toastByDate(date : Int) : String {
        return if (date % 2 == 0 && date % 3 == 0){
            "iOS"
        } else if (date % 2 == 0){
            "Blackberry"
        } else if (date % 3 == 0) {
            "Android"
        } else {
            "Feature Phone"
        }
    }

    fun isPrime(month : Int) : String {
        if (month == 1) return "month is not prime"
        for(i in 2 until month){
            if (month % i == 0) return "month is not prime"
        }
        return "month is prime"
    }

    private var _isPalindrome = MutableLiveData<String>()
    val isPalindrome : LiveData<String> get() = _isPalindrome
    fun palindromeCheck(text: String) {
        val noSpaceText = text.replace("\\s".toRegex(), "")

        var start = 0
        var end = noSpaceText.length-1
        while (start < end){
            if (noSpaceText[start] != noSpaceText[end]){
                _isPalindrome.postValue("not palindrome")
                return
            }
            start++
            end--
        }
        //0123456789
        _isPalindrome.postValue("isPalindrome")
    }
}