package com.example.sportresults.feature_sport_activity.domain.repository

import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.data.local.SportType

interface SportTypeRepository {
    suspend fun getSportType(type: Int): Resource<SportType>

    suspend fun getAll():Resource<List<SportType>>
}