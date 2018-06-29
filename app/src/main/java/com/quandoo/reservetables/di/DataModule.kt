package com.quandoo.reservetables.di

import com.quandoo.reservetables.data.api.ReservationService
import com.quandoo.reservetables.data.api.TableService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal object DataModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideRetrofit(oktHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(oktHttpClient)
            .baseUrl("https://s3-eu-west-1.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideReservationService(retrofit: Retrofit): ReservationService = retrofit.create(ReservationService::class.java)

    @Singleton
    @Provides
    @JvmStatic
    fun provideTableService(retrofit: Retrofit): TableService = retrofit.create(TableService::class.java)

}