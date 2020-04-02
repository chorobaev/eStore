package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.aikosoft.estore.ui.main.MainActivity
import io.aikosoft.estore.ui.main.MainFragmentBindingModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity
}