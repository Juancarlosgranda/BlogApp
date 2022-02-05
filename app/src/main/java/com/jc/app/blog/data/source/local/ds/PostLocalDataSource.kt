package com.jc.app.blog.data.source.local.ds

import com.jc.app.blog.data.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostLocalDataSource {

    fun getPosts(): Flow<List<PostEntity>>

    fun getPost(postId: Int): Flow<PostEntity>

    suspend fun saveAllPosts(postEntityList: List<PostEntity>)

    suspend fun deleteAllPosts()

    suspend fun deletePost(postId: Int)

    suspend fun updatePost(postEntity: PostEntity)


}