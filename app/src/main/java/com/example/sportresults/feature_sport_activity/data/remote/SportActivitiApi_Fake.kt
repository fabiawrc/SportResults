package com.example.sportresults.feature_sport_activity.data.remote

import com.example.sportresults.core.data.dto.BasicApiResponse
import com.example.sportresults.feature_sport_activity.data.local.SportActivityData_Fake
import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto
import javax.inject.Inject

class SportActivitiApi_Fake @Inject constructor(
    private val sportActivityDataFake: SportActivityData_Fake
) : SportActivityApi {
    override suspend fun getActivity(activityId: Long): BasicApiResponse<SportActivityDto> {
        val activity = sportActivityDataFake.sportActivities.find { it.activityId == activityId }
        return BasicApiResponse(data = activity, successful = true, message = null)
    }

    override suspend fun getAllActivities(): BasicApiResponse<List<SportActivityDto>> {
        val activities = sportActivityDataFake.sportActivities
        return BasicApiResponse(data = activities, successful = true, message = null)
    }

    override suspend fun insertActivity(sportActivity: SportActivityDto): BasicApiResponse<Unit> {
        sportActivityDataFake.sportActivities.add(sportActivity)
        return BasicApiResponse(data = Unit, successful = true, message = null)
    }
}