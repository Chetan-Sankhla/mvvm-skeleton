package com.sample.mvvmskeleton.data.local.db

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject constructor(appDatabase: AppDatabase) : DbHelper {

    private val mAppDatabase: AppDatabase = appDatabase
}