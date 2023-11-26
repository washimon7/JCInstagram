package dev.enritech.jcinstagram.login.data.network

import dev.enritech.jcinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET

interface LoginClient {
    @GET("/v3/2b5d942b-147e-4c11-a9bf-cefceb6cb7e2")
    suspend fun doLogin(): Response<LoginResponse>
}