package com.example.sportresults.feature_sport_activity.data.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.repository.StorageTypeRepository

class StorageTypeRepositoryImpl (): StorageTypeRepository
 {
     override suspend fun getStorageType(type: Int): Resource<StorageType> {
         return try {
             Resource.Success(StorageType.getByType(type))
         } catch (e: Exception) {
             Resource.Error(
                 message = e.localizedMessage
             )
         }
     }

     override suspend fun getAll(): Resource<List<StorageType>> {
         return try {
             Resource.Success(StorageType.getList())
         } catch (e: Exception){
             Resource.Error(
                 message = e.localizedMessage
             )
         }
     }
 }