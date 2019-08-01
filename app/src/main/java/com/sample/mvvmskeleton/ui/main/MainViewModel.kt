package com.sample.mvvmskeleton.ui.main

import com.sample.mvvmskeleton.data.DataManager
import com.sample.mvvmskeleton.ui.base.BaseViewModel
import com.sample.mvvmskeleton.utils.rx.SchedulerProvider

class MainViewModel internal constructor(
    dataManager: DataManager, schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {
}