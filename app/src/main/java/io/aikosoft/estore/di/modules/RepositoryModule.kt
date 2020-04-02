package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.Provides
import io.aikosoft.estore.data.network.SampleClient
import io.aikosoft.estore.data.repositories.SampleRepository
import io.aikosoft.estore.data.repositories.implementations.SampleRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesSampleRepository(
        sampleClient: SampleClient
    ): SampleRepository =
        SampleRepositoryImpl(sampleClient)
}