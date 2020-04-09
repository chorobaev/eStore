package io.aikosoft.estore.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.aikosoft.estore.base.CommonViewModel
import io.aikosoft.estore.base.ViewModelFactory
import io.aikosoft.estore.ui.cart.CartViewModel
import io.aikosoft.estore.ui.detail.DetailsViewModel
import io.aikosoft.estore.ui.main.BrowseViewModel
import io.jachoteam.taxiappclient.di.util.ViewModelKey


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CommonViewModel::class)
    abstract fun bindCommonViewModel(commonViewModel: CommonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: BrowseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    abstract fun bindCartViewModel(cartViewModel: CartViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}