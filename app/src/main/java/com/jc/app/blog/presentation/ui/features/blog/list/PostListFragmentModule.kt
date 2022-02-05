package com.jc.app.blog.presentation.ui.features.blog.list

import androidx.lifecycle.ViewModel
import com.jc.app.blog.di.vm.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class PostListFragmentModule {
    @ContributesAndroidInjector
    abstract fun bindPostListFragment(): PostListFragment

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    abstract fun bindPostListViewModel(
        postListViewModel: PostListViewModel
    ): ViewModel
}