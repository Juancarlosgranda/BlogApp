package com.jc.app.blog.di.module

import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.use_case.DeleteAllPosts
import com.jc.app.blog.domain.use_case.DeletePost
import com.jc.app.blog.domain.use_case.GetAndSavePostsFromRemote
import com.jc.app.blog.domain.use_case.GetCommentsByPost
import com.jc.app.blog.domain.use_case.GetPost
import com.jc.app.blog.domain.use_case.GetPostsFromLocal
import com.jc.app.blog.domain.use_case.GetUser
import com.jc.app.blog.domain.use_case.PostUsesCases
import com.jc.app.blog.domain.use_case.UpdatePost
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideUseCaseModule(repository: PostRepository): PostUsesCases {
        return PostUsesCases(
            deleteAllPosts = DeleteAllPosts(repository),
            deletePost = DeletePost(repository),
            getAndSavePostsFromRemote = GetAndSavePostsFromRemote(repository),
            getCommentsByPost = GetCommentsByPost(repository),
            getPostsFromLocal = GetPostsFromLocal(repository),
            getPost = GetPost(repository),
            getUser = GetUser(repository),
            updatePost = UpdatePost(repository)
        )
    }
}