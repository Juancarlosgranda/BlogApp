package com.jc.app.blog.domain.use_case

data class PostUsesCases(
    val deleteAllPosts: DeleteAllPosts,
    val deletePost: DeletePost,
    val getAndSavePostsFromRemote: GetAndSavePostsFromRemote,
    val getCommentsByPost: GetCommentsByPost,
    val getPostsFromLocal: GetPostsFromLocal,
    val getPost: GetPost,
    val getUser: GetUser,
    val updatePost: UpdatePost
)