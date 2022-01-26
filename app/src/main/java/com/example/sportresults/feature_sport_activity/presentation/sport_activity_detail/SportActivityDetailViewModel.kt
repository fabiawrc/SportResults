package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.use_case.SportActivityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportActivityDetailViewModel @Inject constructor(
    private val sportActivityUseCases: SportActivityUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SportActivityDetailState())
    val state: State<SportActivityDetailState> = _state

    init {
        loadSportTypes()
        loadStorageTypes()
    }

    fun onEvent(event: SportActivityDetailEvent) {
        when (event) {
            is SportActivityDetailEvent.SetSportType -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    sportType = state.value.sportTypes.first { type -> type.type == event.type }
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
            }
            is SportActivityDetailEvent.SetStorageType -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    storageType = state.value.storageTypes.first { type -> type.type == event.type }
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
            }
        }
    }


    private fun loadSportTypes() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                sportTypes = SportType.getList()
            )
        }
    }

    private fun loadStorageTypes() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                storageTypes = StorageType.getList()
            )
        }
    }

}