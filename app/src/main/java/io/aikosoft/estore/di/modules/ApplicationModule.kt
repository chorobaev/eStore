package io.aikosoft.estore.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.aikosoft.estore.data.network.ProductClient
import io.aikosoft.estore.data.network.UserClient
import io.aikosoft.estore.di.utils.BaseUrl
import io.aikosoft.estore.di.utils.eStore
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
        AppConstantsModule::class,
        ActivityBindingModule::class,
        ServiceBindingModule::class
    ]
)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient, gson: Gson): Retrofit.Builder =
        Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    @eStore
    fun provideEStoreRetrofit(
        @BaseUrl baseUrl: String,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit = retrofitBuilder.baseUrl(baseUrl).build()

    @Singleton
    @Provides
    fun provideProductClient(@eStore retrofit: Retrofit): ProductClient =
        retrofit.create(ProductClient::class.java)

    @Singleton
    @Provides
    fun provideUserClient(@eStore retrofit: Retrofit): UserClient =
        retrofit.create(UserClient::class.java)


}