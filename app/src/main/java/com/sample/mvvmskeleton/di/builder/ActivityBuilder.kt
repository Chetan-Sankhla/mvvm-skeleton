package com.sample.mvvmskeleton.di.builder

import com.sample.mvvmskeleton.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    internal abstract fun bindMainActivity(): MainActivity
}