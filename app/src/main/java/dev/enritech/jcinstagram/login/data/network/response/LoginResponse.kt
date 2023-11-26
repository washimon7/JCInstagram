package dev.enritech.jcinstagram.login.data.network.response

import com.google.gson.annotations.SerializedName

// Always set @SerializedName for code obfuscation
data class LoginResponse(@SerializedName("success") val loginSuccessfully: Boolean)
