package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPost (
    private val repository: PostRepository,
) {

    operator fun invoke(postId: Int): Flow<PostModel> = repository.getPostFromLocal(postId)

}