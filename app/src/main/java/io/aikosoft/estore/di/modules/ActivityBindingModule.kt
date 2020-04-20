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
interface ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainFragmentBindingModule::class])
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [DetailFragmentBindingModule::class])
    fun bindDetailsActivity(): DetailsActivity

    @ContributesAndroidInjector(modules = [CartFragmentBindingModule::class])
    fun bindCartActivity(): CartActivity
}