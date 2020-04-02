package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.aikosoft.estore.sevices.SampleService

@Module
abstract class ServiceBindingModule {

    @ContributesAndroidInjector
    abstract fun bindLocationService(): SampleService
}