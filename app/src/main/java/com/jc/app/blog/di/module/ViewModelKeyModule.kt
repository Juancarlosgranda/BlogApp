package com.jc.app.blog.di.module

import androidx.lifecycle.ViewModelProvider
import com.jc.app.blog.di.vm.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelKeyModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}