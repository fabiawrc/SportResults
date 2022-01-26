package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.repository.StorageTypeRepository
import javax.inject.Inject

class GetStorageTypeUseCase @Inject constructor(
    private val repository: StorageTypeRepository
) {
    suspend operator fun invoke(type: Int): Resource<StorageType>{
        return repository.getStorageType(type)
    }
}