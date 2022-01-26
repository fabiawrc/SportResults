package com.example.sportresults.feature_sport_activity.domain.model

import com.example.sportresults.feature_sport_activity.data.local.entity.SportActivityEntity
import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import java.util.*

data class SportActivity(
    val activityId: Long,
    val formattedTime: String,
    val sportType: SportType,
    val storageType: StorageType,
    val place: String,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
) {
    fun toActivityEntity():SportActivityEntity{
        return SportActivityEntity(
            activityId = activityId,
            timestamp = Date().time,
            sportType = sportType.type,
            storageType = storageType.type,
            place = place,
            hours = hours,
            minutes = minutes,
            seconds = seconds
        )
    }

    fun toActivityDto(): SportActivityDto {
        return SportActivityDto(
            activityId = activityId,
            timestamp = Date().time,
            sportType = sportType.type,
            storageType = storageType.type,
            place = place,
            hours = hours,
            minutes = minutes,
            seconds = seconds
        )
    }
}
