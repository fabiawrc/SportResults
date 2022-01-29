package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository
import javax.inject.Inject

class GetActivitiesUseCase @Inject constructor(
    private val repositorySport: SportActivityRepository
) {
    suspend operator fun invoke(storageType: StorageType): Resource<List<SportActivity>>{
        return repositorySport.getAllActivities(storageType)
    }
}