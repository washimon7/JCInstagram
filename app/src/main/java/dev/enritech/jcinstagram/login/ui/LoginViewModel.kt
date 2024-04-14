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
    val email: LiveData<String> get() = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password
    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> get() = _isLoginEnabled
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun onLoginChanged(emailValue: String, passwordValue: String) {
        _email.value = emailValue
        _password.value = passwordValue
        _isLoginEnabled.value = checkLoginInputs(emailValue, passwordValue)
        //loginUseCase(email, password)
    }

    private fun checkLoginInputs(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6
    }

    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase.invoke(email.value!!, password.value!!)
            if (result) {
                // Navigate to next screen
                Log.i("LoginViewModel", "Logged in successfully!")
            } else {
                // Log.i("LoginViewModel", "Logged in successfully!")
                Log.i("LoginViewModel", "Error, missmatch credentials")
            }
            _isLoading.value = false
        }
    }
}