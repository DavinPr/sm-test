package com.suitmedia.suitmediatest.ui.home.choice

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
}