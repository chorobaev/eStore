package io.aikosoft.estore.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.aikosoft.estore.base.CommonViewModel
import io.aikosoft.estore.base.ViewModelFactory
import io.aikosoft.estore.ui.main.MainViewModel
import io.jachoteam.taxiappclient.di.util.ViewModelKey


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CommonViewModel::class)
    abstract fun bindCommonViewModel(commonViewModel: CommonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}