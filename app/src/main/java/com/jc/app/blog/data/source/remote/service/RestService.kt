package com.jc.app.blog.data.source.remote.service

import com.jc.app.blog.data.source.remote.response.CommentResponse
import com.jc.app.blog.data.source.remote.response.PostResponse
import com.jc.app.blog.data.source.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface RestService {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostResponse>>

    @GET("users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: Int,
    ): Response<UserResponse>

    @GET("posts/{postId}/comments")
    suspend fun getComments(
        @Path("postId") postId: Int,
    ): Response<List<CommentResponse>>
}