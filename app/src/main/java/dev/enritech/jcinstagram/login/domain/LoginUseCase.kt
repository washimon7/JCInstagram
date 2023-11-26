package dev.enritech.jcinstagram.login.domain

import dev.enritech.jcinstagram.login.data.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()

    suspend operator fun invoke(user: String, pwd: String): Boolean {
        return repository.doLogin(user, pwd)
    }
}