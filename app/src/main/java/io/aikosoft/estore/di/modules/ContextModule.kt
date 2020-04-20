package io.aikosoft.estore.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
interface ContextModule {

    @Binds
    abstract fun provideContext(application: Application): Context
}