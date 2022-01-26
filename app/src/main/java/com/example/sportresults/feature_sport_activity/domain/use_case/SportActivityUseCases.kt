package com.example.sportresults.feature_sport_activity.domain.use_case

data class SportActivityUseCases(
    val getActivitiesUseCase: GetActivitiesUseCase,
    val getActivityUseCase: GetActivityUseCase,
    val insertActivityUseCase: InsertActivityUseCase,
    val getSportTypesUseCase: GetSportTypesUseCase,
    val getSportTypeUseCase: GetSportTypeUseCase,
    val getStorageTypesUseCase: GetStorageTypesUseCase,
    val getStorageTypeUseCase: GetStorageTypeUseCase
)
