package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure

class GetAndSavePostsFromRemote (
    private val repository: PostRepository
) {

    suspend operator fun invoke(): Either<Failure, Unit> {
        return repository.getPostsFromRemote().coMapSuccess {
            repository.saveAllPosts(it)
        }
    }

}