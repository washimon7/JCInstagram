package dev.enritech.jcinstagram.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String>get() = _email

    fun onLoginChanged(value: String) {
        _email.value = value
        // isLoginEnabled = checkLoginInputs(emailValue, passwordValue)
    }
}