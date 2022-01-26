package com.example.sportresults.feature_sport_activity.domain.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType

interface StorageTypeRepository {
    suspend fun getStorageType(type: Int): Resource<StorageType>

    suspend fun getAll():Resource<List<StorageType>>
}