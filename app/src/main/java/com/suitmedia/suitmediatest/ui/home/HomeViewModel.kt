package com.suitmedia.suitmediatest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.suitmediatest.utils.fizzBuzzByDate
import com.suitmedia.suitmediatest.utils.isPalindrome
import com.suitmedia.suitmediatest.utils.isPrime
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _isPalindrome = MutableStateFlow("Your name")
    val isPalindrome = _isPalindrome.asStateFlow()

    private val _name = MutableStateFlow("Your name")
    val name = _name.asStateFlow()

    fun setName(name: String) {
        viewModelScope.launch {
            _name.emit(name)
            _isPalindrome.emit(name.isPalindrome())
        }
    }

    private val _dateResult = MutableSharedFlow<String>()
    val dateResult = _dateResult.asSharedFlow()
    fun setDateResult(date: Int, month: Int) {
        viewModelScope.launch {
            _dateResult.emit(
                """${date.fizzBuzzByDate()}
                |${month.isPrime()}
            """.trimMargin()
            )
        }
    }

    private val _btnEvent = MutableStateFlow("Pilih event")
    val btnEvent = _btnEvent.asStateFlow()
    fun setBtnEventText(text: String) {
        viewModelScope.launch {
            _btnEvent.emit(text)
        }
    }

    private val _btnGuest = MutableStateFlow("Pilih guest")
    val btnGuest = _btnGuest.asStateFlow()
    fun setBtnGuestText(text: String) {
        viewModelScope.launch {
            _btnGuest.emit(text)
        }
    }
}