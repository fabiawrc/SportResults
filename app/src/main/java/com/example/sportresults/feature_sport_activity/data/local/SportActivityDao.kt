package com.example.sportresults.feature_sport_activity.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportresults.feature_sport_activity.data.local.entity.SportActivityEntity

@Dao
interface SportActivityDao {
    @Query("SELECT * FROM SportActivityEntity WHERE activityId = :activityId")
    suspend fun getActivity(activityId: Long): SportActivityEntity

    @Query("SELECT * FROM SportActivityEntity")
    suspend fun getAllActivities(): List<SportActivityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(sportActivity: SportActivityEntity)
}