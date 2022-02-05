package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsFromLocal (
    private val repository: PostRepository,
) {

    operator fun invoke(): Flow<List<PostModel>> = repository.getPostsFromLocal()

}