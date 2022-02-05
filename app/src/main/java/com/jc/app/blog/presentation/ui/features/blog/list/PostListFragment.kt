package com.jc.app.blog.presentation.ui.features.blog.list

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.jc.app.blog.BR
import com.jc.app.blog.R
import com.jc.app.blog.databinding.FragmentPostListBinding
import com.jc.app.blog.presentation.adapters.PostListAdapter
import com.jc.app.blog.presentation.custom.ToggleCustomView
import com.jc.app.blog.presentation.extensions.setSafeOnClickListener
import com.jc.app.blog.presentation.extensions.showToast
import com.jc.app.blog.presentation.ui.base.BaseFragmentWithViewModel
import com.jc.app.blog.presentation.ui.model.PostFilter
import kotlinx.coroutines.flow.collect

class PostListFragment : BaseFragmentWithViewModel<FragmentPostListBinding, PostListViewModel>() {

    override val getBindingVariable: Int
        get() = BR.postListVM

    override val getLayoutId: Int
        get() = R.layout.fragment_post_list

    override fun getViewModel(): PostListViewModel {
        return ViewModelProvider(this, viewModelFactory)[PostListViewModel::class.java]
    }


    override fun onFragmentViewReady() {
        val postListAdapter = PostListAdapter { model ->
            val action = PostListFragmentDirections
                .actionPostListFragmentToPostFragment(model.id, model.userId)
            navigateTo(action)
        }
        viewDataBinding.rvPosts.adapter = postListAdapter

        lifecycleScope.launchWhenStarted {
            myViewModel.showErrorCause.collect { show ->
                if (show) requireActivity().showToast(myViewModel.errorCause)
            }
        }

    }

    override fun onSetupListeners() {

        viewDataBinding.ttbFilters.buttonsOnClickListener {
            when (it) {
                ToggleCustomView.ButtonOption.ONE -> myViewModel.filterSelected =
                    PostFilter.ALL_POSTS
                ToggleCustomView.ButtonOption.TWO -> myViewModel.filterSelected =
                    PostFilter.FAVORITES
            }
            myViewModel.setPostModelLit(myViewModel.allPosts.value?: emptyList())
        }

        viewDataBinding.fab.setSafeOnClickListener {
            myViewModel.deleteAllPosts()
        }
        viewDataBinding.ivRefresh.setSafeOnClickListener {
            myViewModel.reloadPosts()
        }
        viewDataBinding.btnLoadPosts.setSafeOnClickListener {
            myViewModel.reloadPosts()
        }
    }
}