package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository
import javax.inject.Inject

class InsertActivityUseCase @Inject constructor(
    private val repositorySport: SportActivityRepository
) {
    suspend operator fun invoke(sportActivity: SportActivity): Resource<Unit>{
        return repositorySport.insertActivity(sportActivity)
    }
}