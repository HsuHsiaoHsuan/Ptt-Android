package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class CommentList(
    @SerializedName("list")
    val list: List<Comment>?,
    @SerializedName("next_idx")
    val nextIdx: String?
)
