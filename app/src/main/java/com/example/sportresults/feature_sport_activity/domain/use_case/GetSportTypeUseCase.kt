package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.domain.repository.SportTypeRepository
import javax.inject.Inject

class GetSportTypeUseCase @Inject constructor(
    private val repository: SportTypeRepository
) {
    suspend operator fun invoke(type: Int): Resource<SportType>{
        return repository.getSportType(type)
    }
}