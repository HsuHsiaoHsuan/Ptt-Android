package tw.y_studio.ptt.model.retrofit

import com.google.gson.annotations.SerializedName

data class ArticleList(
    @SerializedName("list")
    val list: List<ArticleSummary>?,
    @SerializedName("next_idx")
    val nextIdx: String?
)
