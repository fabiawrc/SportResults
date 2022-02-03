package com.example.sportresults.feature_sport_activity.data.local

import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto

data class SportActivityData_Fake(
    val sportActivities: MutableList<SportActivityDto> = mutableListOf()
)
{
    init {
    }
}
