package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class AccountRegisterParams(
    @SerializedName("address")
    val address: String?,
    @SerializedName("career")
    val career: String?,
    @SerializedName("client_id")
    val clientId: String?,
    @SerializedName("client_secret")
    val clientSecret: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("over18")
    val over18: Boolean?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("password_confirm")
    val passwordConfirm: String?,
    @SerializedName("realname")
    val realname: String?,
    @SerializedName("username")
    val username: String?
)
