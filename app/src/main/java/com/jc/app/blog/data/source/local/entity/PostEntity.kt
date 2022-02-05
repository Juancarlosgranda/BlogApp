package com.jc.app.blog.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jc.app.blog.domain.model.PostModel

@Entity
data class PostEntity (
    @ColumnInfo(name = "id")
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "user_id") val userId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean
){
    fun toPostModel(): PostModel {
        return PostModel(
            id,
            userId,
            title,
            body,
            favorite
        )
    }
}

