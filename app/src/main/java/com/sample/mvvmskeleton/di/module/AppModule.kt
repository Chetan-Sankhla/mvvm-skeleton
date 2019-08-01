package com.sample.mvvmskeleton.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sample.mvvmskeleton.data.AppDataManager
import com.sample.mvvmskeleton.data.DataManager
import com.sample.mvvmskeleton.data.local.db.AppDatabase
import com.sample.mvvmskeleton.data.local.db.AppDbHelper
import com.sample.mvvmskeleton.data.local.db.DbHelper
import com.sample.mvvmskeleton.data.local.prefs.AppPreferencesHelper
import com.sample.mvvmskeleton.data.local.prefs.PreferencesHelper
import com.sample.mvvmskeleton.data.remote.ApiHelper
import com.sample.mvvmskeleton.di.DatabaseInfo
import com.sample.mvvmskeleton.di.PreferenceInfo
import com.sample.mvvmskeleton.utils.AppConstants
import com.sample.mvvmskeleton.utils.rx.AppSchedulerProvider
import com.sample.mvvmskeleton.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return AppConstants.DB_NAME
    }

    @Provides
    @PreferenceInfo
    fun providePreferenceName(): String {
        return AppConstants.PREF_NAME
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDbHelper): DbHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    @Singleton
    fun providePreferencesHelper(appPreferencesHelper: AppPreferencesHelper): PreferencesHelper {
        return appPreferencesHelper
    }

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("http://starlord.hackerearth.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .client(client)
        .build()

    @Provides
    @Singleton
    fun providesApiHelper(retrofit: Retrofit): ApiHelper = retrofit.create(ApiHelper::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).fallbackToDestructiveMigration()
            .build()
    }
}