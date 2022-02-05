package com.jc.app.blog.di.module

import com.jc.app.blog.data.repository.PostRepositoryImpl
import com.jc.app.blog.domain.repository.PostRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DataSourceModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun providePostRepository(postRepository: PostRepositoryImpl): PostRepository

}