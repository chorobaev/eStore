package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.Provides
import io.aikosoft.estore.data.network.ProductClient
import io.aikosoft.estore.data.network.UserClient
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.data.repositories.implementations.ProductRepositoryImpl
import io.aikosoft.estore.data.repositories.implementations.UserRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProductRepository(
        productClient: ProductClient
    ): ProductRepository =
        ProductRepositoryImpl(
            productClient
        )

    @Singleton
    @Provides
    fun provideUserRepository(
        userClient: UserClient
    ): UserRepository =
        UserRepositoryImpl(
            userClient
        )
}