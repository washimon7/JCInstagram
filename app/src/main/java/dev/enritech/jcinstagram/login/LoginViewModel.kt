package dev.enritech.jcinstagram.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String>get() = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String>get() = _password
    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean>get() = _isLoginEnabled

    fun onLoginChanged(emailValue: String, passwordValue: String) {
        _email.value = emailValue
        _password.value = passwordValue
        _isLoginEnabled.value = checkLoginInputs(emailValue, passwordValue)
    }
    private fun checkLoginInputs(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }
}