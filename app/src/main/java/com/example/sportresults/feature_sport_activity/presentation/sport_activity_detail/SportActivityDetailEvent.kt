package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

sealed class SportActivityDetailEvent {
    object ClickSave : SportActivityDetailEvent()
    data class SetSportType(val type: Int) : SportActivityDetailEvent()
    data class SetPlace(val name: String) : SportActivityDetailEvent()
    data class SetHours(val value: Int) : SportActivityDetailEvent()
    data class SetMinutes(val value: Int) : SportActivityDetailEvent()
    data class SetSeconds(val value: Int) : SportActivityDetailEvent()
    data class SetStorageType(val type: Int) : SportActivityDetailEvent()
    object SetShowDurationDialog: SportActivityDetailEvent()
}
