package com.jc.app.blog.presentation.ui.features.blog.post

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.jc.app.blog.BR
import com.jc.app.blog.R
import com.jc.app.blog.databinding.FragmentPostBinding
import com.jc.app.blog.presentation.adapters.CommentListAdapter
import com.jc.app.blog.presentation.extensions.setSafeOnClickListener
import com.jc.app.blog.presentation.extensions.showToast
import com.jc.app.blog.presentation.ui.base.BaseFragmentWithViewModel
import kotlinx.coroutines.flow.collect


class PostFragment: BaseFragmentWithViewModel<FragmentPostBinding, PostViewModel>() {

    override val getBindingVariable: Int
        get() = BR.postVM

    override val getLayoutId: Int
        get() = R.layout.fragment_post

    private val args: PostFragmentArgs by navArgs()

    override fun getViewModel(): PostViewModel {
        return ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
    }

    override fun onFragmentViewReady() {
        myViewModel.setParameterFromArguments(args.postId, args.postId)
        viewDataBinding.rvComments.adapter = CommentListAdapter()

        lifecycleScope.launchWhenStarted {
            myViewModel.showErrorCause.collect { show ->
                if (show) requireActivity().showToast(myViewModel.errorCause)
            }
        }

        myViewModel.postDeleted.observe(viewLifecycleOwner){
            goBack()
        }

    }

    override fun onSetupListeners() {

        viewDataBinding.ivFavorite.setSafeOnClickListener(safeTime = 2000) {
            myViewModel.apply {
                setIsFavorite(isFavorite = isFavorite.get().not())
            }
        }

        viewDataBinding.fab.setSafeOnClickListener {
            myViewModel.deletePost()
        }
    }

}