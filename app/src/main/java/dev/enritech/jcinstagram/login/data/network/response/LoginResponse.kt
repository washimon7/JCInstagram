package dev.enritech.jcinstagram.login.data.network.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(@SerializedName("success") val loggedIn: Boolean)