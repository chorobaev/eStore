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
interface ViewModelBindingModule {

    @Binds
    @IntoMap
    @ViewModelKey(CommonViewModel::class)
    fun bindCommonViewModel(commonViewModel: CommonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BrowseViewModel::class)
    fun bindMainViewModel(mainViewModel: BrowseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun bindDetailViewModel(detailViewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(cartViewModel: CartViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}