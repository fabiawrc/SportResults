package com.example.sportresults.feature_sport_activity.data.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.repository.StorageTypeRepository

class StorageTypeRepositoryImpl() : StorageTypeRepository {
    override suspend fun getStorageType(type: Int): Resource<StorageType> {
        return try {
            Resource.Success(StorageType.getByType(type))
        } catch (e: Exception) {
            Resource.Error(
                message = e.localizedMessage
            )
        }
    }

    override suspend fun getAll(forDbOperation: Boolean?): Resource<List<StorageType>> {
        return try {
            var data = StorageType.getList()

            forDbOperation?.let {
                data = data.filter { storageType -> storageType.forDbOperation == it }
            }
            Resource.Success(data)
        } catch (e: Exception) {
            Resource.Error(
                message = e.localizedMessage
            )
        }
    }
}