package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.aikosoft.estore.ui.cart.CartActivity
import io.aikosoft.estore.ui.cart.CartFragmentBindingModule
import io.aikosoft.estore.ui.detail.DetailsActivity
import io.aikosoft.estore.ui.detail.DetailFragmentBindingModule
import io.aikosoft.estore.ui.main.MainActivity
import io.aikosoft.estore.ui.main.MainFragmentBindingModule

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailFragmentBindingModule::class])
    abstract fun bindDetailsActivity(): DetailsActivity

    @ContributesAndroidInjector(modules = [CartFragmentBindingModule::class])
    abstract fun bindCartActivity(): CartActivity
}