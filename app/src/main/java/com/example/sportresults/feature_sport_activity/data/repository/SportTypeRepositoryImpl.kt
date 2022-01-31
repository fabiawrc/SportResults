package com.example.sportresults.feature_sport_activity.data.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.core.util.UiText
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.domain.repository.SportTypeRepository

class SportTypeRepositoryImpl: SportTypeRepository {
    override suspend fun getSportType(type: Int): Resource<SportType> {
        return try {
            Resource.Success(SportType.getByType(type))
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.DynamicString(e.localizedMessage)
            )
        }
    }

    override suspend fun getAll(): Resource<List<SportType>> {
        return try {
            Resource.Success(SportType.getList())
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.DynamicString(e.localizedMessage)
            )
        }
    }
}