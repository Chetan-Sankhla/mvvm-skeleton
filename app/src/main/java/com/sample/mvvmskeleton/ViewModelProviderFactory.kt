package com.sample.mvvmskeleton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.mvvmskeleton.data.DataManager
import com.sample.mvvmskeleton.ui.main.MainViewModel
import com.sample.mvvmskeleton.utils.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(val dataManager: DataManager, val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(
                dataManager,
                schedulerProvider
            ) as T
            else -> throw IllegalArgumentException("Unknown View Model Class: ${modelClass.name}")
        }
    }
}