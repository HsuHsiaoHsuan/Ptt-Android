package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class ArticleSummary(
    @SerializedName("aid")
    val aid: String?,
    @SerializedName("bid")
    val bid: String?,
    @SerializedName("deleted")
    val deleted: Boolean?,
    @SerializedName("filename")
    val filename: String?,
    @SerializedName("create_time")
    val createTime: Int?,
    @SerializedName("modified")
    val modified: Int?,
    @SerializedName("recommend")
    val recommend: Int?,
    @SerializedName("owner")
    val owner: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("money")
    val money: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("class")
    val articleClass: String?,
    @SerializedName("mode")
    val mode: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("read")
    val read: Boolean?
)
