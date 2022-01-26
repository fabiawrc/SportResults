package com.example.sportresults.feature_sport_activity.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import java.text.SimpleDateFormat
import java.util.*

@Entity
data class SportActivityEntity(
    @PrimaryKey val activityId: Long,
    val timestamp: Long,
    val sportType: Int,
    val storageType: Int,
    val place: String,
    val hours: Int = 0,
    val minutes: Int = 0,
    val seconds: Int = 0
)
{
    fun toActivity():SportActivity{
        return SportActivity(
            activityId = activityId,
            sportType = when(sportType) {
                SportType.Running.type -> SportType.Running
                SportType.Bike.type -> SportType.Bike
                else -> SportType.Running
            },
            storageType = when(storageType) {
                StorageType.All.type -> StorageType.All
                StorageType.Local.type -> StorageType.Local
                StorageType.Remote.type -> StorageType.Remote
                else -> StorageType.All
            },
            place = place,
            hours = hours,
            minutes = minutes,
            formattedTime = SimpleDateFormat(
                "MMM dd, HH:mm", Locale.getDefault()
            ).run {
                format(timestamp)
            }
        )
    }
}
