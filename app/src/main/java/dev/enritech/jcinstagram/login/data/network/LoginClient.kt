package dev.enritech.jcinstagram.login.data.network

import dev.enritech.jcinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/0591d43d-68f3-4c63-9f51-ec4b0202ed28")
    suspend fun doLogin(): Response<LoginResponse>
}