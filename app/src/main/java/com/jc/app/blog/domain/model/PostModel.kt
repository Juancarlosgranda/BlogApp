package com.jc.app.blog.domain.model

data class PostModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val favorite: Boolean = false
)
