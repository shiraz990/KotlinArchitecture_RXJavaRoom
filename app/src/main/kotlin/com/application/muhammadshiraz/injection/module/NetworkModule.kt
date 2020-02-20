package com.application.muhammadshiraz.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import com.application.muhammadshiraz.network.NYTimesApi
import com.application.muhammadshiraz.utils.BASE_URL_NYTIME
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Module which provides all required dependencies about network
 */
@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {
    /**
     * Provides the NYTimes service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the NYTimes service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideNYTimesApi(retrofit: Retrofit): NYTimesApi {
        return retrofit.create(NYTimesApi::class.java)
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL_NYTIME)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
    }
}