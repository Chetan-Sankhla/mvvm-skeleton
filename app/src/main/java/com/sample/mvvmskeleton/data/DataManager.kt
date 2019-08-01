package com.sample.mvvmskeleton.data

import com.sample.mvvmskeleton.data.local.db.DbHelper
import com.sample.mvvmskeleton.data.local.prefs.PreferencesHelper

interface DataManager: DbHelper, PreferencesHelper {
}