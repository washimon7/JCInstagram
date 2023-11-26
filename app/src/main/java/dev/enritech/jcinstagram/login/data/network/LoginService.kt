package dev.enritech.jcinstagram.login.data.network

import dev.enritech.jcinstagram.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    val retrofit = RetrofitHelper.getRetrofit()

    suspend fun doLogin(user: String, pwd: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(LoginClient::class.java).doLogin()
            response.body()?.loginSuccessfully ?: false
        }
    }
}