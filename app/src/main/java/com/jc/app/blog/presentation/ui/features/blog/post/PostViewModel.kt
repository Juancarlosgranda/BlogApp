package com.jc.app.blog.presentation.ui.features.blog.post

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jc.app.blog.domain.model.CommentModel
import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.model.UserModel
import com.jc.app.blog.domain.use_case.PostUsesCases
import com.jc.app.blog.presentation.ui.base.BaseViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class PostViewModel @Inject constructor(
    private val postUsesCases: PostUsesCases
) : BaseViewModel() {
    private var postId by Delegates.notNull<Int>()
    private var userId by Delegates.notNull<Int>()
    private val _postModel = MutableLiveData<PostModel>()
    val postModel: LiveData<PostModel>
        get() = _postModel


    private val _userInfo = MutableLiveData<UserModel>()
    val userInfo: LiveData<UserModel>
        get() = _userInfo

    private val _commentList: MutableLiveData<List<CommentModel>> = MutableLiveData()
    val commentList: LiveData<List<CommentModel>>
        get() = _commentList

    val isFavorite = ObservableBoolean(false)

    val postDeleted = MutableLiveData<Boolean>()

    fun setParameterFromArguments(postId: Int, userId: Int) {
        this.postId = postId
        this.userId = userId
        getPost()
        getData()
    }

    private fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            postUsesCases.getPost(postId)
                .catch { e -> Log.e(e.cause.toString(), "message: $e ", )}
                .collect { post ->
                    withContext(Dispatchers.Main) {
                        _postModel.value = post
                        isFavorite.set(post.favorite)
                    }
                }
        }
    }

    private fun getData() {
        _spinner.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val getComments = async { postUsesCases.getCommentsByPost(postId) }
            val getUserInfo = async { postUsesCases.getUser(userId) }
            val resultComments = getComments.await()
            val resultUser = getUserInfo.await()
            withContext(Dispatchers.Main) {
                resultComments.either(::handleUseCaseFailureFromBase, ::setComments)
                resultUser.either(::handleUseCaseFailureFromBase, ::setUserModel)
                _spinner.value = false
            }
        }
    }

    fun deletePost(){
        viewModelScope.launch(Dispatchers.IO) {
            postUsesCases.deletePost(postId)
            withContext(Dispatchers.Main){
                postDeleted.value = true
            }
        }
    }

    private fun updatePostModel(){
        viewModelScope.launch(Dispatchers.IO) {
            postModel.value?.let {
                postUsesCases.updatePost(it.copy(favorite = isFavorite.get()))
            }
        }
    }

    fun setIsFavorite(isFavorite: Boolean){
        this.isFavorite.set(isFavorite)
        updatePostModel()
    }

    private fun setUserModel(userModel: UserModel) {
        _userInfo.value = userModel
    }

    private fun setComments(commentsList: List<CommentModel>) {
        _commentList.value = commentsList
    }

}