package com.example.sportresults.feature_sport_activity.data.local

import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto

data class SportActivityMemoryData(
    val sportActivities: MutableList<SportActivityDto> = mutableListOf()
)
{
    init {
    }
}
