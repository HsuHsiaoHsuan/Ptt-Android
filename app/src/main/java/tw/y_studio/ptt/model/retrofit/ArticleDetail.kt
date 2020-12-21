package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class ArticleDetail(
    @SerializedName("aid")
    val aid: String?,
    @SerializedName("bbs")
    val bbs: String?,
    @SerializedName("bid")
    val bid: String?,
    @SerializedName("brdname")
    val brdname: String?,
    @SerializedName("class")
    val classX: String?,
    @SerializedName("content")
    val content: List<List<Rune>>?,
    @SerializedName("create_time")
    val createTime: Int?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("deleted")
    val deleted: Boolean?,
    @SerializedName("filename")
    val filename: String?,
    @SerializedName("host")
    val host: String?,
    @SerializedName("ip")
    val ip: String?,
    @SerializedName("mode")
    val mode: Int?,
    @SerializedName("modified")
    val modified: Int?,
    @SerializedName("money")
    val money: Int?,
    @SerializedName("owner")
    val owner: String?,
    @SerializedName("read")
    val read: Boolean?,
    @SerializedName("recommend")
    val recommend: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?
)
