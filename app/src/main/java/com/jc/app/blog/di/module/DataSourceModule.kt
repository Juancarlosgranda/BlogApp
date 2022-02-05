package com.jc.app.blog.di.module

import com.jc.app.blog.data.source.local.ds.PostLocalDataSource
import com.jc.app.blog.data.source.local.ds.PostLocalDataSourceImpl
import com.jc.app.blog.data.source.remote.ds.PostRemoteDataSource
import com.jc.app.blog.data.source.remote.ds.PostRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun providePostRemoteDataSource(
        postRemoteDataSource: PostRemoteDataSourceImpl
    ): PostRemoteDataSource

    @Binds
    abstract fun providePostLocalDataSource(
        postLocalDataSource: PostLocalDataSourceImpl
    ): PostLocalDataSource

}