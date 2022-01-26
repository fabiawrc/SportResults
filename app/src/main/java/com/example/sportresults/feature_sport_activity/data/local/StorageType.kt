package com.example.sportresults.feature_sport_activity.data.local

import com.example.sportresults.core.domain.model.DropDownContentData

sealed class StorageType(val type: Int, val name:String, val color: String) {
    object All : StorageType(0, "All", "")
    object Local : StorageType(1, "Local", "")
    object Remote : StorageType(2, "Remote", "")

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
                text = storageType.name,
                value = storageType.type
            )
        }
    }
}
