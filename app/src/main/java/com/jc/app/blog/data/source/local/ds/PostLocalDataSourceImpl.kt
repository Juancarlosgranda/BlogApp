package com.jc.app.blog.data.source.local.ds

import com.jc.app.blog.data.source.local.dao.PostDao
import com.jc.app.blog.data.source.local.entity.PostEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class PostLocalDataSourceImpl @Inject constructor(
    private val dao: PostDao
): PostLocalDataSource {

    override fun getPosts(): Flow<List<PostEntity>> {
        return dao.getPosts()
    }

    override fun getPost(postId: Int): Flow<PostEntity> {
        return dao.getPost(postId)
    }

    override suspend fun saveAllPosts(postEntityList: List<PostEntity>) {
        dao.insertAll(postEntityList)
    }

    override suspend fun deleteAllPosts() {
        dao.deleteAll()
    }

    override suspend fun deletePost(postId: Int) {
        dao.deleteById(postId)
    }

    override suspend fun updatePost(postEntity: PostEntity) {
        dao.insert(postEntity)
    }
}