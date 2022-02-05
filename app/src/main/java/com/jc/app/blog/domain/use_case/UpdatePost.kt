package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.repository.PostRepository

class UpdatePost(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postModel: PostModel) {
        repository.updatePost(postModel)
    }
}