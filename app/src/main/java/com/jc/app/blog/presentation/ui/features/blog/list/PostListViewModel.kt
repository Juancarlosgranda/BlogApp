package com.jc.app.blog.presentation.ui.features.blog.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jc.app.blog.domain.model.PostModel
import com.jc.app.blog.domain.use_case.PostUsesCases
import com.jc.app.blog.presentation.ui.base.BaseViewModel
import com.jc.app.blog.presentation.ui.model.PostFilter
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostListViewModel @Inject constructor(
    private val postUsesCases: PostUsesCases
) : BaseViewModel() {

    val allPosts: MutableLiveData<List<PostModel>> = MutableLiveData()
    private val _postModelList: MutableLiveData<List<PostModel>> = MutableLiveData()
    val postModelList: LiveData<List<PostModel>>
        get() = _postModelList

    var filterSelected: PostFilter = PostFilter.ALL_POSTS

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            postUsesCases.getPostsFromLocal()
                .catch { e -> Log.e(e.cause.toString(), "message: $e ", )}
                .collect { postModelList ->
                    withContext(Dispatchers.Main) {
                        allPosts.value = postModelList
                        setPostModelLit(postModelList)
                    }
                }
        }
    }

    fun setPostModelLit(postModelList: List<PostModel>) {
        when (filterSelected) {
            PostFilter.ALL_POSTS -> {
                _postModelList.value = postModelList.sortedByDescending { it.favorite }
            }
            PostFilter.FAVORITES -> {
                _postModelList.value = postModelList.filter { it.favorite }
            }
        }
    }

    fun reloadPosts() {
        _spinner.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = postUsesCases.getAndSavePostsFromRemote()
            withContext(Dispatchers.Main) {
                result.either(::handleUseCaseFailureFromBase) {}
                _spinner.value = false
            }
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            postUsesCases.deleteAllPosts()
        }
    }

}