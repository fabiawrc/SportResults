package com.example.sportresults.feature_sport_activity.data.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository

class FakeSportActivityRepository : SportActivityRepository {

    private val sportActivities = mutableListOf<SportActivity>()

    override suspend fun getActivity(
        activityId: Long,
        storageType: StorageType
    ): Resource<SportActivity> {
        return Resource.Success(data = sportActivities.find { it.activityId == activityId })
    }

    override suspend fun getAllActivities(storageType: StorageType): Resource<List<SportActivity>> {
        var activities = emptyList<SportActivity>()

        when (storageType) {
            is StorageType.All -> {
                activities = sportActivities
            }
            is StorageType.Local -> {
                activities = sportActivities.filter { it.storageType == storageType }
            }
            is StorageType.Remote -> {
                activities = sportActivities.filter { it.storageType == storageType }
            }
        }
        return Resource.Success(data = activities)
    }

    override suspend fun insertActivity(sportActivity: SportActivity): Resource<Unit> {
        sportActivities.add(sportActivity)
        return Resource.Success(data = Unit)
    }
}