package com.sample.mvvmskeleton.di.component

import android.app.Application
import com.sample.mvvmskeleton.MvvmSkeletonApplication
import com.sample.mvvmskeleton.di.builder.ActivityBuilder
import com.sample.mvvmskeleton.di.module.AppModule
import com.sample.mvvmskeleton.di.module.OkHttpModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        OkHttpModule::class
    ]
)
interface AppComponent {

    fun inject(app: MvvmSkeletonApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}