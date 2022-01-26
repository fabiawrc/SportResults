package com.example.sportresults.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sportresults.feature_sport_activity.data.local.SportActivityDao
import com.example.sportresults.feature_sport_activity.data.local.entity.SportActivityEntity

@Database(
    entities = [SportActivityEntity::class],
    version = 1
)
abstract class AppLocalDatabase: RoomDatabase() {
    abstract val sportActivityDao: SportActivityDao
}