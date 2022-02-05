package com.jc.app.blog.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jc.app.blog.data.source.remote.response.CommentResponse
import com.jc.app.blog.data.source.remote.response.PostResponse
import com.jc.app.blog.data.source.remote.response.UserResponse
import java.lang.reflect.Type

object BlogModelData {

    fun provideRemotePostsFromAssets(): List<PostResponse> {
        val listType: Type = object : TypeToken<List<PostResponse>>() {}.type

        return Gson().fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(fileName = "postList.json"),
            listType
        ) ?: emptyList()
    }

    fun provideUserFromAssets(): UserResponse? {
        val listType: Type = object : TypeToken<UserResponse>() {}.type

        return Gson().fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(fileName = "user.json"),
            listType
        )
    }

    fun provideCommentsFromAssets(): List<CommentResponse> {
        val listType: Type = object : TypeToken<List<CommentResponse>>() {}.type

        return Gson().fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(fileName = "commentList.json"),
            listType
        ) ?: emptyList()
    }


}