package com.sample.mvvmskeleton.data

import android.content.Context
import com.google.gson.Gson
import com.sample.mvvmskeleton.data.local.db.DbHelper
import com.sample.mvvmskeleton.data.local.prefs.PreferencesHelper
import com.sample.mvvmskeleton.data.remote.ApiHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    context: Context,
    dbHelper: DbHelper,
    preferencesHelper: PreferencesHelper,
    apiHelper: ApiHelper,
    gson: Gson
) : DataManager {

    private val mApiHelper: ApiHelper = apiHelper
    private val mContext: Context = context
    private val mDbHelper: DbHelper = dbHelper
    private val mGson: Gson = gson
    private val mPreferencesHelper: PreferencesHelper = preferencesHelper

}