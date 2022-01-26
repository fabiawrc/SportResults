package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.domain.repository.SportTypeRepository
import javax.inject.Inject

class GetSportTypesUseCase @Inject constructor(
    private val repository: SportTypeRepository
) {
    suspend operator fun invoke(): Resource<List<SportType>>{
        return repository.getAll()
    }
}