package io.aikosoft.estore.ui.detail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindOverviewFragment(): OverviewFragment

    @ContributesAndroidInjector
    abstract fun bindRelatedFragment(): RelatedFragment

    @ContributesAndroidInjector
    abstract fun bindImageFragment(): ImageFragment
}