package com.jc.app.blog.di.module

import android.content.Context
import androidx.room.Room
import com.jc.app.blog.data.source.local.BlogDataBase
import com.jc.app.blog.data.source.local.dao.PostDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataBaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(appContext: Context): BlogDataBase {
        return Room
            .databaseBuilder(appContext, BlogDataBase::class.java, BlogDataBase.DATABASE_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun providePostDao(dataBase: BlogDataBase): PostDao {
        return dataBase.getPostDao()
    }

}