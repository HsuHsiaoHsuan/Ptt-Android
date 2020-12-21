package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class Color(
    @SerializedName("background")
    val background: Int?,
    @SerializedName("blink")
    val blink: Boolean?,
    @SerializedName("foreground")
    val foreground: Int?,
    @SerializedName("highlight")
    val highlight: Boolean?
)
