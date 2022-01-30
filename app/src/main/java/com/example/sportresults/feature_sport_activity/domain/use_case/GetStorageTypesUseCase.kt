package com.example.sportresults.feature_sport_activity.domain.use_case

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.repository.StorageTypeRepository
import javax.inject.Inject

class GetStorageTypesUseCase @Inject constructor(
    private val repository: StorageTypeRepository
) {
    suspend operator fun invoke(forDbOperation: Boolean?): Resource<List<StorageType>>{
        return repository.getAll(forDbOperation)
    }
}