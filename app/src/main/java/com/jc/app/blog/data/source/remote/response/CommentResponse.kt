package com.jc.app.blog.data.source.remote.response

import com.jc.app.blog.domain.model.CommentModel

data class CommentResponse(
    val body: String
){
    fun toCommentModel(): CommentModel {
        return CommentModel(body)
    }
}
