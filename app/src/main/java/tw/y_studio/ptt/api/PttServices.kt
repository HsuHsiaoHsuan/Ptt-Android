package tw.y_studio.ptt.api

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tw.y_studio.ptt.BuildConfig
import tw.y_studio.ptt.model.retrofit.*

// https://api.devptt.site:5000

interface PttServices {

    // article
    /**
     * 列出熱門文章
     * @param startIdx starting idx, '' if fetch from the beginning.
     * @param limit max number of the returned list, requiring <= 300
     * @param desc descending or ascending
     */
    @GET("/articles/popular")
    suspend fun getPopularArticle(
        @Query("startIdx") startIdx: String? = "",
        @Query("limit") limit: Int? = 300,
        @Query("desc") desc: Boolean? = true
    ): Response<ArticleList>

    /**
     * 讀取文章裡置頂 comments
     * @param bid board id
     * @param aid article id
     */
    @GET("/board/{bid}/article/{aid}/firstcomments")
    suspend fun getArticleFirstComments(
        @Path("bid") bid: String,
        @Path("aid") aid: String
    ): Response<CommentList>

    /**
     * 讀取置底文章
     * @param bid board bid obtained from board-list
     */
    @GET("/board/{bid}/articles/bottom")
    suspend fun getBottomArticle(
        @Path("bid") bid: String
    ): Response<ArticleList>

    /**
     * 讀取文章裡的 comments (從最新開始往以前拉資料. (response 時舊的排在前面.))
     * @param bid board id
     * @param aid article id
     * @param startIdx starting idx, '' if fetch from the beginning.
     * @param limit max number of the returned list, requiring <= 300
     * @param desc descending or ascending
     */
    @GET("/board/{bid}/article/{aid}/comments")
    suspend fun getArticleComments(
        @Path("bid") bid: String,
        @Path("aid") aid: String,
        @Query("startIdx") startIdx: String? = "",
        @Query("limit") limit: Int? = 300,
        @Query("desc") desc: Boolean? = true
    ): Response<CommentList>

    /**
     * 讀取板裡的文章列表
     * @param bid board id
     * @param title '' returns all articles
     * @param startIdx starting idx, '' if fetch from the beginning.
     * @param limit max number of the returned list, requiring <= 300
     * @param desc descending or ascending
     */
    @GET("/board/{bid}/articles")
    suspend fun getArticleList(
        @Path("bid") bid: String,
        @Query("title") title: String? = "",
        @Query("startIdx") startIdx: String? = "",
        @Query("limit") limit: Int? = 300,
        @Query("desc") desc: Boolean? = true
    ): Response<ArticleList>

    /**
     * 文章的詳細資訊
     * @param bid board id
     * @param aid article id
     * @param fields fields to retrieve, separated by ,(comma)
     */
    @GET("/board/{bid}/article/{aid}")
    suspend fun getArticleContent(
        @Path("bid") bid: String,
        @Path("aid") aid: String,
        @Query("fields") fields: String? = "",
    ): Response<ArticleDetail>

    /**
     * 列出使用者的所有文章.
     * @param userId user's account id
     * @param startIdx starting idx, '' if fetch from the beginning.
     * @param limit max number of the returned list, requiring <= 300
     * @param desc descending or ascending
     */
    @GET("/user/{user_id}/articles")
    suspend fun getUserArticles(
        @Path("user_id") userId: String,
        @Query("startIdx") startIdx: String? = "",
        @Query("limit") limit: Int? = 300,
        @Query("desc") desc: Boolean? = true
    ): Response<ArticleList>

    // user
    /**
     * AccountRegister
     */
    @POST("/account/register")
    suspend fun registerAccount(
        @Body body: AccountRegisterParams
    ): Response<AccessToken>

    /**
     * Login
     */
    @POST("/account/login")
    suspend fun login(
        @Body body: AccountLoginParams
    ): Response<AccessToken>

    /**
     * 讀取板的上線板友名單
     * @param bid board id
     * @param name
     * @param startIdx starting idx, '' if fetch from the beginning.
     * @param limit max number of the returned list, requiring <= 300
     * @param desc descending or ascending
     */
    @GET("/board/{bid}/users")
    suspend fun getOnlineUserLise(
        @Path("bid") bid: String,
        @Path("name") name: String,
        @Query("startIdx") startIdx: String? = "",
        @Query("limit") limit: Int? = 300,
        @Query("desc") desc: Boolean? = true
    )

    companion object {
        private var instance: PttServices? = null

        fun getInstance(): PttServices {
            if (instance == null) {
                synchronized(this) {
                    val client = OkHttpClient.Builder().build()
                    instance = Retrofit.Builder()
                        .baseUrl(BuildConfig.API_Domain)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(PttServices::class.java)
                }
            }
            return instance!!
        }
    }
}
