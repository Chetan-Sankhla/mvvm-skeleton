package com.sample.mvvmskeleton.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.sample.mvvmskeleton.BR
import com.sample.mvvmskeleton.R
import com.sample.mvvmskeleton.databinding.ActivityMainBinding
import com.sample.mvvmskeleton.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    lateinit var mMainViewModel: MainViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        return mMainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}
