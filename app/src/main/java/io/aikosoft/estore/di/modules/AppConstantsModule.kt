package io.aikosoft.estore.di.modules

import dagger.Module
import dagger.Provides
import io.aikosoft.estore.di.utils.BaseUrl
import javax.inject.Singleton

@Module
class AppConstantsModule {

    @get:Provides
    @get:Singleton
    @get:BaseUrl
    val baseUrl: String = "https://www.google.com"
}