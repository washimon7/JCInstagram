package dev.enritech.jcinstagram.login.data

import dev.enritech.jcinstagram.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun doLogin(user: String, pwd: String): Boolean {
        return api.doLogin(user, pwd)
    }
}