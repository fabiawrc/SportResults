package com.example.sportresults.feature_sport_activity.data.local

import com.example.sportresults.R
import com.example.sportresults.core.domain.model.DropDownContentData

sealed class SportType(val type: Int, val name: String, val hasDistance: Boolean, val icon: Int) {
    object Running : SportType(0, "Běh", true, R.drawable.ic_baseline_directions_run_24)
    object Bike : SportType(1, "Cyklistika", true, R.drawable.ic_baseline_directions_bike_24)
    object Tenis : SportType(2, "Tenis", false, R.drawable.tennis)

    companion object {
        fun getList(): List<SportType> {
            var list: MutableList<SportType> = mutableListOf()

            SportType::class.sealedSubclasses.forEach {
                it.objectInstance?.let { classInst ->
                    list.add(classInst)
                }
            }
            return list.toList()
        }

        fun getByType(type: Int): SportType {
            return SportType::class.sealedSubclasses
                .firstOrNull { it -> it.objectInstance?.type == type }
                ?.objectInstance
                ?: Running
        }

        fun toDropDownContentData(sportType: SportType):DropDownContentData{
            return DropDownContentData(
                icon = sportType.icon,
                text = sportType.name,
                value = sportType.type
            )
        }
    }
}

