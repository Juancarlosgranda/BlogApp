package com.jc.app.blog.data.source.remote.ds

import com.jc.app.blog.data.source.remote.response.CommentResponse
import com.jc.app.blog.data.source.remote.response.PostResponse
import com.jc.app.blog.data.source.remote.response.UserResponse
import com.jc.app.blog.data.source.remote.service.RestService
import com.jc.app.blog.data.utils.CallServiceBase
import com.jc.app.blog.data.utils.ConnectionUtils
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import javax.inject.Inject

class PostRemoteDataSourceImpl @Inject constructor(
    private val restService: RestService,
    private val connectionUtils: ConnectionUtils
) : PostRemoteDataSource, CallServiceBase()  {

    override fun getNetworkUtils() = connectionUtils

    override suspend fun getPosts(): Either<Failure, List<PostResponse>> = callService {
        restService.getPosts()
    }

    override suspend fun getUser(userId: Int): Either<Failure, UserResponse> = callService {
        restService.getUser(userId)
    }

    override suspend fun getCommentsByPost(
        postId: Int
    ): Either<Failure, List<CommentResponse>> = callService {
        restService.getComments(postId)
    }

}