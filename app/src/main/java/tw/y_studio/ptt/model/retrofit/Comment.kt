package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("aid")
    val aid: String?,
    @SerializedName("commentID")
    val commentId: String?,
    @SerializedName("commentType")
    val commentType: String?,
    @SerializedName("content")
    val content: List<Rune>?,
    @SerializedName("fromHost")
    val fromHost: String?,
    @SerializedName("fromIP")
    val fromIp: String?,
    @SerializedName("postDateTime")
    val postDateTime: String?,
    @SerializedName("replyCommentID")
    val replyCommentId: String?,
    @SerializedName("userID")
    val userId: String?
)
