package ru.polenova.ncraftmedia.api

import retrofit2.http.*
import retrofit2.Response
import ru.polenova.ncraftmedia.dto.PostModel
import ru.polenova.ncraftmedia.dto.PostRequestDto

interface API {
    @POST("api/v1/authentication")
    suspend fun authenticate(@Body authRequestParams: AuthRequestParams): Response<Token>

    @POST("api/v1/registration")
    suspend fun register(@Body authRequestParams: AuthRequestParams): Response<Token>

    @POST("api/v1/posts")
    suspend fun createPost(@Body postRequestDto: PostRequestDto): Response<Void>

    @GET("api/v1/posts")
    suspend fun getPosts(): Response<List<PostModel>>

    @POST("api/v1/posts/{id}/like")
    suspend fun likedByUser(@Path("id") id: Long): Response<PostModel>

    @DELETE("api/v1/posts/{id}/dislike")
    suspend fun dislikedByUser(@Path("id") id: Long): Response<PostModel>

    @POST("api/v1/posts/{id}/repost")
    suspend fun repostedByUser(
        @Path("id") id: Long,
        @Body postRequestDto: PostRequestDto
    ): Response<PostModel>

    @GET("api/v1/posts/recent")
    suspend fun getRecent(): Response<List<PostModel>>

    @GET("api/v1/posts/{id}/get-posts-after")
    suspend fun getPostsAfter(@Path("id") id: Long): Response<List<PostModel>>

    @GET("api/v1/posts/{id}/get-posts-before")
    suspend fun getPostsBefore(@Path("id") id: Long): Response<List<PostModel>>
}