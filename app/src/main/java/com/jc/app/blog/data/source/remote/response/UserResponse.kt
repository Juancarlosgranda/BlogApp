package com.jc.app.blog.data.source.remote.response

import com.jc.app.blog.domain.model.UserModel

data class UserResponse(
    val name: String,
    val email: String,
    val phone: String,
    val website: String
) {
    fun toUserModel(): UserModel {
        return UserModel(name, email, phone, website)
    }
}
