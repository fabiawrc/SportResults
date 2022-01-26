package com.example.sportresults.feature_sport_activity.data.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.SportActivityDao
import com.example.sportresults.feature_sport_activity.data.remote.SportActivityApi
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository
import javax.inject.Inject

class SportActivityRepositoryImpl @Inject constructor(
    private val daoSport: SportActivityDao,
    private val apiSport: SportActivityApi
) : SportActivityRepository {

    override suspend fun getActivity(activityId: Long,storageType: StorageType): Resource<SportActivity> {
        return try {
            var responseData: SportActivity? = null
            when (storageType) {
                is StorageType.Local -> {
                    responseData = daoSport.getActivity(activityId).toActivity()
                }
                is StorageType.Remote -> {
                    responseData = apiSport.getActivity(activityId.toString()).data?.toActivity()
                }
                is StorageType.All -> {

                }
            }
            Resource.Success(data = responseData)
        } catch (e: Exception) {
            Resource.Error(
                message = e.localizedMessage
            )
        }
    }

    override suspend fun getAllActivities(storageType: StorageType): Resource<List<SportActivity>> {
        return try {
            var responseData: List<SportActivity>? = emptyList()
            when (storageType) {
                is StorageType.Local -> {
                    responseData = daoSport.getAllActivities().map { it.toActivity() }
                }
                is StorageType.Remote -> {
                    responseData = apiSport.getAllActivities().data?.map { it.toActivity() }
                }
                is StorageType.All -> {

                }
            }
            Resource.Success(data = responseData)
        } catch (e: Exception) {
            Resource.Error(
                message = e.localizedMessage
            )
        }
    }

    override suspend fun insertActivity(
        sportActivity: SportActivity,
        storageType: StorageType
    ): Resource<Unit> {
        return try {
            when (storageType) {
                is StorageType.Local -> {
                    daoSport.insertActivity(sportActivity.toActivityEntity())
                }
                is StorageType.Remote -> {
                    apiSport.insertActivity(sportActivity.toActivityDto())
                }
                is StorageType.All -> {
                    daoSport.insertActivity(sportActivity.toActivityEntity())
                    apiSport.insertActivity(sportActivity.toActivityDto())
                }
            }
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(
                message = e.localizedMessage
            )
        }
    }
}