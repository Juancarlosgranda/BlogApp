package com.jc.app.blog.domain.repository

import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.model.UserModel
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPostsFromRemote(): Either<Failure, List<PostModel>>

    fun getPostsFromLocal(): Flow<List<PostModel>>

    fun getPostFromLocal(postId: Int): Flow<PostModel>

    suspend fun saveAllPosts(postModelList: List<PostModel>)

    suspend fun getUser(userId: Int): Either<Failure, UserModel>

    suspend fun getCommentsByPost(postId: Int): Either<Failure, List<CommentModel>>

    suspend fun deleteAllPosts()

    suspend fun deletePost(postId: Int)

    suspend fun updatePost(postModel: PostModel)

}