package dev.enritech.jcinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.enritech.jcinstagram.login.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginUseCase = LoginUseCase()
    private val _email = MutableLiveData<String>()
    private val _pwd = MutableLiveData<String>()
    private val _couldLogin = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    val email: LiveData<String> = _email
    val pwd: LiveData<String> = _pwd
    val couldLogin = _couldLogin
    val isLoading = _isLoading

    fun onLoginChanged(emailValue: String, pwdValue: String) {
        _email.value = emailValue
        _pwd.value = pwdValue
        _couldLogin.value = checkLoginInputs(emailValue, pwdValue)
    }
    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val loggedInSuccessfully = loginUseCase(email.value!!, pwd.value!!)

            if (loggedInSuccessfully) {
                // Navigate to next screen
                Log.i("JCILogin", "Ok")
            } else {
                Log.i("JCILogin", "Error")
            }
            _isLoading.value = false
        }
    }
    private fun checkLoginInputs(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }
}