package com.jc.app.blog.data.source.remote.ds

import com.jc.app.blog.data.source.remote.response.CommentResponse
import com.jc.app.blog.data.source.remote.response.PostResponse
import com.jc.app.blog.data.source.remote.response.UserResponse
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure

interface PostRemoteDataSource {

    suspend fun getPosts(): Either<Failure, List<PostResponse>>

    suspend fun getUser(userId: Int): Either<Failure, UserResponse>

    suspend fun getCommentsByPost(postId: Int): Either<Failure, List<CommentResponse>>

}