package com.jc.app.blog.presentation.ui.features.blog.post

import androidx.lifecycle.ViewModel
import com.jc.app.blog.di.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PostFragmentModule {

    @ContributesAndroidInjector
    abstract fun bindPostFragment(): PostFragment

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    abstract fun bindPostListViewModel(
        postViewModel: PostViewModel
    ): ViewModel
}