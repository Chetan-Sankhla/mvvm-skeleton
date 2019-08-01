package com.sample.mvvmskeleton.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.mvvmskeleton.data.model.db.Beer

@Database(entities = [Beer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {



}