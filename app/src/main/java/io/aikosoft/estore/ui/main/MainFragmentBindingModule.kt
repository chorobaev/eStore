package io.aikosoft.estore.ui.main

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindBrowseFragment(): BrowseFragment

    @ContributesAndroidInjector
    abstract fun bindBrowsePageFragment(): BrowsePageFragment
}