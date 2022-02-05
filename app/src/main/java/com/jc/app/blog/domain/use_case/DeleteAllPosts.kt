package com.jc.app.blog.domain.use_case

import com.jc.app.blog.domain.repository.PostRepository

class DeleteAllPosts (
    private val repository: PostRepository
) {

    suspend operator fun invoke(){
        repository.deleteAllPosts()
    }
}