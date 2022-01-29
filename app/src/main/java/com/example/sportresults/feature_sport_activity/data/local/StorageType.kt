package com.example.sportresults.feature_sport_activity.data.local

import androidx.compose.ui.graphics.Color
import com.example.sportresults.R
import com.example.sportresults.core.domain.model.DropDownContentData
import com.example.sportresults.core.presentation.ui.theme.BlueAccent
import com.example.sportresults.core.presentation.ui.theme.GreenSuccess
import com.example.sportresults.core.presentation.ui.theme.RedDanger

sealed class StorageType(val type: Int, val name:String, val color: Color, val icon: Int) {
    object All : StorageType(0, "Vše", BlueAccent, R.drawable.check_all)
    object Local : StorageType(1, "Lokální", GreenSuccess, R.drawable.database)
    object Remote : StorageType(2, "Vzdálené", RedDanger, R.drawable.server_network)

    companion object {
        fun getList(): List<StorageType> {
            var list: MutableList<StorageType> = mutableListOf()

            StorageType::class.sealedSubclasses.forEach {
                it.objectInstance?.let { classInst ->
                    list.add(classInst)
                }
            }
            return list.toList()
        }

        fun getByType(type: Int): StorageType {
            return StorageType::class.sealedSubclasses
                .firstOrNull { it -> it.objectInstance?.type == type }
                ?.objectInstance
                ?: All
        }

        fun toDropDownContentData(storageType: StorageType): DropDownContentData {
            return DropDownContentData(
                icon = storageType.icon,
                text = storageType.name,
                value = storageType.type
            )
        }
    }
}
