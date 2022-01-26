package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.repository.SportActivityRepository
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val repositorySport: SportActivityRepository
) {
    suspend operator fun invoke(activityId: Long): Resource<SportActivity>{
        return repositorySport.getActivity(activityId)
    }
}