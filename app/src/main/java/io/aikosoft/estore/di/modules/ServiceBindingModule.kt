package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.aikosoft.estore.sevices.SampleService

@Module
interface ServiceBindingModule {

    @ContributesAndroidInjector
    fun bindLocationService(): SampleService
}