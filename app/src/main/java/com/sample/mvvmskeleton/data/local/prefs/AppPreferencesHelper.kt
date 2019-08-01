package com.sample.mvvmskeleton.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.sample.mvvmskeleton.di.PreferenceInfo
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Context, @PreferenceInfo prefFileName: String) :
    PreferencesHelper {

    private var mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
}