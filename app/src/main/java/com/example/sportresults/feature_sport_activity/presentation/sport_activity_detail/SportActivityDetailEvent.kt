package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

sealed class SportActivityDetailEvent {
    object ClickSave : SportActivityDetailEvent()
    data class SetSportType(val type: Int) : SportActivityDetailEvent()
    data class SetPlace(val name: String) : SportActivityDetailEvent()
    data class SetDuration(val duration: Int) : SportActivityDetailEvent()
    data class SetStorageType(val type: Int) : SportActivityDetailEvent()
}
