package com.jc.app.blog.presentation.ui.features

import com.jc.app.blog.di.scopes.PerActivityScope
import com.jc.app.blog.presentation.ui.MainActivity
import com.jc.app.blog.presentation.ui.features.blog.list.PostListFragmentModule
import com.jc.app.blog.presentation.ui.features.blog.post.PostFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeatureMainModule {

    @PerActivityScope
    @ContributesAndroidInjector(modules = [
        PostListFragmentModule::class,
        PostFragmentModule::class
    ])
    abstract fun bindMainActivity(): MainActivity

}