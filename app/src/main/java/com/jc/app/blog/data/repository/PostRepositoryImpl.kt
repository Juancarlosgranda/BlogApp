package com.jc.app.blog.data.repository

import com.jc.app.blog.data.source.local.ds.PostLocalDataSource
import com.jc.app.blog.data.source.local.entity.PostEntity
import com.jc.app.blog.data.source.remote.ds.PostRemoteDataSource
import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.model.UserModel
import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PostRepositoryImpl @Inject constructor(
    private val remoteDS: PostRemoteDataSource,
    private val localDS: PostLocalDataSource
) : PostRepository {

    override suspend fun getPostsFromRemote(): Either<Failure, List<PostModel>> {
        return remoteDS.getPosts().coMapSuccess { response ->
            response.map { it.toPostModel() }
        }
    }

    override fun getPostsFromLocal(): Flow<List<PostModel>> = localDS
        .getPosts()
        .map { entityList ->
            entityList.map { it.toPostModel() }
        }

    override fun getPostFromLocal(postId: Int): Flow<PostModel> = localDS
        .getPost(postId)
        .map { it.toPostModel() }

    override suspend fun saveAllPosts(postModelList: List<PostModel>) {
        localDS.saveAllPosts(
            postModelList.map { PostEntity(it.id, it.userId, it.title, it.body, favorite = false) }
        )
    }

    override suspend fun getUser(userId: Int): Either<Failure, UserModel> {
        return remoteDS.getUser(userId).coMapSuccess { response ->
            response.toUserModel()
        }
    }

    override suspend fun getCommentsByPost(postId: Int): Either<Failure, List<CommentModel>> {
        return remoteDS.getCommentsByPost(postId).coMapSuccess { response ->
            response.map { it.toCommentModel() }
        }
    }

    override suspend fun deleteAllPosts() {
        localDS.deleteAllPosts()
    }

    override suspend fun deletePost(postId: Int) {
        localDS.deletePost(postId)
    }

    override suspend fun updatePost(postModel: PostModel) {
        localDS.updatePost(
            PostEntity(postModel.id, postModel.userId, postModel.title, postModel.body, postModel.favorite)
        )
    }
}