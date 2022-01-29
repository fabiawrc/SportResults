package com.example.sportresults.feature_sport_activity.domain.model

import com.example.sportresults.core.domain.model.UserDurationTime
import com.example.sportresults.core.util.TimeCalc
import com.example.sportresults.feature_sport_activity.data.local.entity.SportActivityEntity
import com.example.sportresults.feature_sport_activity.data.remote.dto.SportActivityDto
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import java.util.*

data class SportActivity(
    val activityId: Long = 0,
    val formattedTime: String = "",
    val sportType: SportType? = null,
    var storageType: StorageType? = null,
    val place: String = "",
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0,
) {
    fun toActivityEntity(): SportActivityEntity {
        val duration = TimeCalc.getDuration(UserDurationTime(hours, minutes, seconds))

        return SportActivityEntity(
            activityId = activityId,
            timestamp = Date().time,
            sportType = sportType!!.type,
            storageType = storageType!!.type,
            place = place,
            duration = duration
        )
    }

    fun toActivityDto(): SportActivityDto {
        val duration = TimeCalc.getDuration(UserDurationTime(hours, minutes, seconds))

        return SportActivityDto(
            activityId = activityId,
            timestamp = Date().time,
            sportType = sportType!!.type,
            storageType = storageType!!.type,
            place = place,
            duration = duration
        )
    }

    fun getFormatedDuration(): String {
        var result = ""

        if (hours > 0)
            result += "${hours}h"
        if (minutes > 0)
            result += "${minutes}min"
        if (seconds > 0)
            result += "${seconds}s"

        return result
    }
}
