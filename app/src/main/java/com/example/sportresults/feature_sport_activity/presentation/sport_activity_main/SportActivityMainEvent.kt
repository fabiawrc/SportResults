package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import com.example.sportresults.feature_sport_activity.data.local.StorageType

sealed class SportActivityMainEvent {
    object ClickNewSportActivity : SportActivityMainEvent()
    data class SportActivityItemClick(val activityId: Long): SportActivityMainEvent()
    data class StorageTypeChange(val sorageType: StorageType): SportActivityMainEvent()
}
