package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.Provides
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.data.repositories.implementations.ProductRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesSampleRepository(): ProductRepository =
        ProductRepositoryImpl()
}