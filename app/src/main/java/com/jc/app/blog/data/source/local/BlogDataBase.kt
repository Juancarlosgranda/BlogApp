package com.jc.app.blog.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jc.app.blog.data.source.local.dao.PostDao
import com.jc.app.blog.data.source.local.entity.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BlogDataBase : RoomDatabase()  {

    abstract fun getPostDao(): PostDao

    companion object {
        const val DATABASE_NAME = "blog_db"
    }
}