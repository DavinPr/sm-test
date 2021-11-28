package com.suitmedia.suitmediatest.ui.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.suitmediatest.utils.fizzBuzzByDate
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _fragmentTag = MutableStateFlow("Your fragmentTag")
    val fragmentTag = _fragmentTag.asStateFlow()
    fun setFragmentTag(fragmentTag: String) {
        viewModelScope.launch {
            _fragmentTag.emit(fragmentTag)
        }
    }

    private val _fragment = MutableStateFlow<Fragment?>(null)
    val fragment = _fragment.asStateFlow()
    fun setFragment(fragment: Fragment) {
        viewModelScope.launch {
            _fragment.emit(fragment)
        }
    }

    private val _name = MutableStateFlow("Your name")
    val name = _name.asStateFlow()
    fun setName(name: String) {
        viewModelScope.launch {
            _name.emit(name)
        }
    }

    private val _phone = MutableSharedFlow<String>()
    val phone = _phone.asSharedFlow()
    fun setPhone(date: Int) {
        viewModelScope.launch {
            _phone.emit(date.fizzBuzzByDate())
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