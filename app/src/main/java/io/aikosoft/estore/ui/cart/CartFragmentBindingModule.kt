package io.aikosoft.estore.ui.cart

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CartFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindCartFragment(): CartFragment

    @ContributesAndroidInjector
    abstract fun bindShoppingPaymentFragment(): ShoppingPaymentFragment
}