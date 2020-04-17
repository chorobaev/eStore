package io.aikosoft.estore.ui.cart

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CartFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun bindCartFragment(): CartFragment

    @ContributesAndroidInjector
    abstract fun bindShoppingPaymentFragment(): ShoppingPaymentFragment

    @ContributesAndroidInjector
    abstract fun bindOrderSummaryFragment(): OrderSummaryFragment

    @ContributesAndroidInjector
    abstract fun bindQuantitySelectorFragment(): QuantitySelectorFragment
}