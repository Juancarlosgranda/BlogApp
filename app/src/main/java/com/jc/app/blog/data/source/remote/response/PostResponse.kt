package com.jc.app.blog.data.source.remote.response

import com.jc.app.blog.domain.model.PostModel

data class PostResponse(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toPostModel(): PostModel {
        return PostModel(id, userId, title, body)
    }
}
