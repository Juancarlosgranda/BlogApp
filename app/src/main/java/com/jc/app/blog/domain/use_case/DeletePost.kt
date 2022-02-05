package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository

class DeletePost (
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: Int) {
        repository.deletePost(postId)
    }
}