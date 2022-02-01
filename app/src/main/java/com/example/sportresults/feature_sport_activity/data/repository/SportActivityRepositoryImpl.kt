package com.example.sportresults.feature_sport_activity.data.repository

import com.example.sportresults.R
import com.example.sportresults.core.util.Resource
import com.example.sportresults.core.util.UiText
import com.example.sportresults.feature_sport_activity.data.local.SportActivityDao
import com.example.sportresults.feature_sport_activity.data.remote.SportActivityApi
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SportActivityRepositoryImpl @Inject constructor(
    private val dao: SportActivityDao,
    private val api: SportActivityApi
) : SportActivityRepository {

    override suspend fun getActivity(
        activityId: Long,
        storageType: StorageType
    ): Resource<SportActivity> {
        return try {
            var responseData: SportActivity? = null
            when (storageType) {
                is StorageType.Local -> {
                    responseData = dao.getActivity(activityId).toActivity()
                }
                is StorageType.Remote -> {
                    responseData = api.getActivity(activityId).data?.toActivity()
                }
            }
            Resource.Success(data = responseData)
        } catch (e: IOException) {
            Resource.Error(uiText = UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.DynamicString(e.localizedMessage)
            )
        }
    }

    override suspend fun getAllActivities(storageType: StorageType): Resource<List<SportActivity>> {
        var responseData: MutableList<SportActivity>? = mutableListOf()

        return try {
            when (storageType) {
                is StorageType.Local -> {
                    responseData =
                        dao.getAllActivities().map { it.toActivity() }.toMutableList()
                }
                is StorageType.Remote -> {
                    responseData =
                        api.getAllActivities().data?.map { it.toActivity() }?.toMutableList()
                }
                is StorageType.All -> {
                    val localData = dao.getAllActivities().map { it.toActivity() }
                    responseData?.addAll(localData)
                    val remoteData = api.getAllActivities().data?.map { it.toActivity() }
                    responseData?.addAll(remoteData ?: emptyList())
                }
            }
            responseData?.sortByDescending { it.formattedTime }
            Resource.Success(data = responseData)
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server),
                data = responseData
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong),
                data = responseData
            )
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.DynamicString(e.localizedMessage),
                data = responseData
            )
        }
    }

    override suspend fun insertActivity(
        sportActivity: SportActivity
    ): Resource<Unit> {
        return try {
            when (sportActivity.storageType) {
                is StorageType.Local -> {
                    dao.insertActivity(sportActivity.toActivityEntity())
                }
                is StorageType.Remote -> {
                    api.insertActivity(sportActivity.toActivityDto())
                }
                is StorageType.All -> {
                    sportActivity.storageType = StorageType.Local
                    dao.insertActivity(sportActivity.toActivityEntity())
                    sportActivity.storageType = StorageType.Remote
                    api.insertActivity(sportActivity.toActivityDto())
                }
            }
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(uiText = UiText.StringResource(R.string.error_couldnt_reach_server))
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.DynamicString(e.localizedMessage)
            )
        }
    }
}