package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.UserModel
import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure

class GetUser(
    private val repository: PostRepository
) {

    suspend operator fun invoke(userId: Int): Either<Failure, UserModel> {
        return repository.getUser(userId)
    }
}