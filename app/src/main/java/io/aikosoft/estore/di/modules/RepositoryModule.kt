package io.aikosoft.estore.di.modules

import dagger.Binds
import dagger.Module
import io.aikosoft.estore.data.repositories.ProductRepository
import io.aikosoft.estore.data.repositories.UserRepository
import io.aikosoft.estore.data.repositories.implementations.ProductRepositoryImpl
import io.aikosoft.estore.data.repositories.implementations.UserRepositoryImpl

@Module
interface RepositoryModule {

    @Binds
    fun bindsProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    fun bindsUserRepository(impl: UserRepositoryImpl): UserRepository
}