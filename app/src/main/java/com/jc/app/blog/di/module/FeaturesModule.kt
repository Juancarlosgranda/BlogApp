package com.jc.app.blog.di.module

import com.jc.app.blog.presentation.ui.features.FeatureMainModule
import dagger.Module

@Module(includes = [
    FeatureMainModule::class,
])
abstract class FeaturesModule