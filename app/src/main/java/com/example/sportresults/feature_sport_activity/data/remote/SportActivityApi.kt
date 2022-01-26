package com.example.sportresults.feature_sport_activity.data.remote

import com.example.sportresults.core.data.dto.BasicApiResponse
import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SportActivityApi {

    @GET("/api/activity/get")
    suspend fun getActivity(
        @Query("activityId") activityId: String
    ): BasicApiResponse<SportActivityDto>

    @GET("/api/activity/getall")
    suspend fun getAllActivities(): BasicApiResponse<List<SportActivityDto>>


    @POST("/api/activity/insert")
    suspend fun insertActivity(
        @Body sportActivity: SportActivityDto
    ): BasicApiResponse<Unit>
}