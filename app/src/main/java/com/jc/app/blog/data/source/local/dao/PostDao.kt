package com.jc.app.blog.data.source.local.dao

import androidx.room.*
import com.jc.app.blog.data.source.local.base.BaseDao
import com.jc.app.blog.data.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao : BaseDao<PostEntity> {

    @Query("SELECT * from PostEntity")
    fun getPosts(): Flow<List<PostEntity>>

    @Query("SELECT * from PostEntity WHERE id = :postId")
    fun getPost(postId: Int): Flow<PostEntity>

    @Query("DELETE FROM PostEntity WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM PostEntity")
    suspend fun deleteAll()

}