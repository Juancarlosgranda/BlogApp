package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.repository.PostRepository
import com.jc.app.blog.domain.utils.Either
import com.jc.app.blog.domain.utils.Failure

class GetCommentsByPost (
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: Int): Either<Failure, List<CommentModel>> {
        return repository.getCommentsByPost(postId)
    }

}