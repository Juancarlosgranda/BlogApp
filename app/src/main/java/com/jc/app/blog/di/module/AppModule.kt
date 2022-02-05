package com.jc.app.blog.di.module

import android.content.Context
import com.jc.app.blog.BlogApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(app: BlogApplication): Context {
        return app.applicationContext
    }
}