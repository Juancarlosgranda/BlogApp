package com.jc.app.blog.di.component

import com.jc.app.blog.BlogApplication
import com.jc.app.blog.di.module.AppModule
import com.jc.app.blog.di.module.FeaturesModule
import com.jc.app.blog.di.module.LocalDataBaseModule
import com.jc.app.blog.di.module.NetworkModule
import com.jc.app.blog.di.module.RepositoryModule
import com.jc.app.blog.di.module.UseCaseModule
import com.jc.app.blog.di.module.ViewModelKeyModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,  // provide singleton app context
    ViewModelKeyModule::class,  // view model factory.
    LocalDataBaseModule::class, // Room
    NetworkModule::class, // retrofit
    RepositoryModule::class, // Singletons Repositories
    FeaturesModule::class,
    UseCaseModule::class,
])
interface AppComponent : AndroidInjector<BlogApplication>{

    @Component.Factory
    interface Factory : AndroidInjector.Factory<BlogApplication>

}