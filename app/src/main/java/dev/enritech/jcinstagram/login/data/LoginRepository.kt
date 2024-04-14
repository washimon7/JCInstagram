package dev.enritech.jcinstagram.login.data

import dev.enritech.jcinstagram.login.data.network.LoginService

class LoginRepository {
    private val api = LoginService()

    suspend fun doLogin(user: String, password: String): Boolean {
        // Aquí poner la lógica de pedir al api de internet, del local o de donde sea
        return api.doLogin(user, password)
    }
}