package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class AccountLoginParams(
    @SerializedName("client_id")
    val clientId: String?,
    @SerializedName("client_secret")
    val clientSecret: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("username")
    val username: String?
)
