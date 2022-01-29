package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity

data class SportActivityDetailState(
    val isLoading: Boolean = false,
    val sportActivity: SportActivity? = SportActivity(),
    val sportTypes: List<SportType> = emptyList(),
    val storageTypes: List<StorageType> = emptyList(),
    val showDurationDialog: Boolean = false,
    val canSave: Boolean = false
)