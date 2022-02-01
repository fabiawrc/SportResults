package com.example.sportresults.feature_sport_activity.data.remote

import com.example.sportresults.core.data.dto.BasicApiResponse
import com.example.sportresults.feature_sport_activity.data.local.SportActivityMemoryData
import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto
import javax.inject.Inject

class SportActivitiApi_MockTest @Inject constructor(
    private val sportActivityMemoryData: SportActivityMemoryData
) : SportActivityApi {
    override suspend fun getActivity(activityId: Long): BasicApiResponse<SportActivityDto> {
        val activity = sportActivityMemoryData.sportActivities.find { it.activityId == activityId }
        return BasicApiResponse(data = activity, successful = true, message = null)
    }

    override suspend fun getAllActivities(): BasicApiResponse<List<SportActivityDto>> {
        val activities = sportActivityMemoryData.sportActivities
        return BasicApiResponse(data = activities, successful = true, message = null)
    }

    override suspend fun insertActivity(sportActivity: SportActivityDto): BasicApiResponse<Unit> {
        sportActivityMemoryData.sportActivities.add(sportActivity)
        return BasicApiResponse(data = Unit, successful = true, message = null)
    }
}