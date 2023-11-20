package dev.enritech.jcinstagram.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    private val _pwd = MutableLiveData<String>()
    private val _couldLogin = MutableLiveData<Boolean>()
    val email: LiveData<String> = _email
    val pwd: LiveData<String> = _pwd
    val couldLogin = _couldLogin

    fun onLoginChanged(emailValue: String, pwdValue: String) {
        _email.value = emailValue
        _pwd.value = pwdValue
        _couldLogin.value = checkLoginInputs(emailValue, pwdValue)
    }
    private fun checkLoginInputs(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }
}