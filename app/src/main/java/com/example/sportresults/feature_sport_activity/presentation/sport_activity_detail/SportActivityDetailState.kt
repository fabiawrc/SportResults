package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType

data class SportActivityDetailState(
    val isLoading: Boolean = false,
    val sportTypes:List<SportType> = emptyList(),
    val storageTypes: List<StorageType> = emptyList(),
)