package com.example.sportresults.feature_sport_activity.data.remote.dto

import com.example.sportresults.core.util.TimeCalc
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import java.text.SimpleDateFormat
import java.util.*

data class SportActivityDto(
    val activityId: Long = 0,
    val timestamp: Long,
    val sportType: Int,
    val storageType: Int,
    val place: String,
    val duration: Int = 0,
    val distance: Float = 0f
) {
    fun toActivity(): SportActivity {
        val userDurationTime = TimeCalc.getUserDurationTime(duration)

        return SportActivity(
            activityId = activityId,
            sportType = SportType.getByType(sportType),
            storageType = StorageType.getByType(storageType),
            place = place,
            hours = userDurationTime.hours,
            minutes = userDurationTime.minutes,
            seconds = userDurationTime.seconds,
            distance = distance,
            formattedTime = SimpleDateFormat(
                "MMM dd, HH:mm", Locale.getDefault()
            ).run {
                format(timestamp)
            }
        )
    }
}
