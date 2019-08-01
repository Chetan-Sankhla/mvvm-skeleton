package com.sample.mvvmskeleton.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.sample.mvvmskeleton.data.DataManager
import com.sample.mvvmskeleton.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> internal constructor(
    private val mDataManager: DataManager,
    private val mSchedulerProvider: SchedulerProvider
) : ViewModel() {

    private val mIsLoading = ObservableBoolean()

    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    private var mNavigator: WeakReference<N>? = null

    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun getNavigator(): N? {
        return mNavigator?.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getDataManager(): DataManager {
        return mDataManager
    }

    fun getSchedulerProvider(): SchedulerProvider {
        return mSchedulerProvider
    }
}