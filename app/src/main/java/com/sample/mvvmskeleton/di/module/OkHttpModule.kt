package com.sample.mvvmskeleton.di.module

import android.app.Application
import android.content.Context
import com.sample.mvvmskeleton.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class OkHttpModule {

    private fun getBaseBuilder(cache: Cache): OkHttpClient.Builder {

        return OkHttpClient.Builder()
            /*.addNetworkInterceptor()*/
            .cache(cache)
            .retryOnConnectionFailure(true)
            .connectTimeout(150, TimeUnit.SECONDS)
            .readTimeout(150, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
    }

    private class CachingControlInterceptor(private val context: Context) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            val cacheControl = CacheControl.Builder()
                .maxStale(1, TimeUnit.MINUTES)
                .maxAge(1, TimeUnit.MINUTES)
                .build()
            return if (NetworkUtils.isNetworkConnected(context)) {
                requestBuilder.cacheControl(cacheControl)
                requestBuilder.header("Content-Type", "application/json")
                requestBuilder.header("Cache-Control", "public, max-age=" + 30)

                val request = requestBuilder.build()

                val originalResponse = chain.proceed(request)

                originalResponse.newBuilder().build()
            } else {
                requestBuilder.header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                )

                val request = requestBuilder.build()

                val originalResponse = chain.proceed(request)

                originalResponse.newBuilder().build()
            }
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache =
        Cache(application.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesOkHttp(
        cache: Cache,
        loggingInterceptor: HttpLoggingInterceptor, context: Context
    ): OkHttpClient = getBaseBuilder(cache)
        .addInterceptor(CachingControlInterceptor(context))
        .addInterceptor(loggingInterceptor)
        .build()
}