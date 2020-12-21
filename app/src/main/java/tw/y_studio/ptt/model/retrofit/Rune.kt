package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class Rune(
    @SerializedName("color0")
    val color0: Color?,
    @SerializedName("color1")
    val color1: Color?,
    @SerializedName("text")
    val text: String?
)
