package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity

data class SportActivityMainState(
    val sportActivities: List<SportActivity> = emptyList(),
    val storageTypes: List<StorageType> = emptyList(),
    val selectedStorageType: StorageType = StorageType.All,
    val isLoading: Boolean = false,
)
