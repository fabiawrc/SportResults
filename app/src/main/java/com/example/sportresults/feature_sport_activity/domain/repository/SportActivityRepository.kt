package com.example.sportresults.feature_sport_activity.domain.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity

interface SportActivityRepository {
    suspend fun getActivity(activityId: Long, storageType: StorageType = StorageType.All): Resource<SportActivity>

    suspend fun getAllActivities(storageType: StorageType = StorageType.All): Resource<List<SportActivity>>

    suspend fun insertActivity(sportActivity: SportActivity, storageType: StorageType = StorageType.All): Resource<Unit>
}