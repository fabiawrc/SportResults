package com.example.sportresults.feature_sport_activity.presentation.sport_activity_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportresults.R
import com.example.sportresults.core.util.Resource
import com.example.sportresults.core.util.Screen
import com.example.sportresults.core.util.UiEvent
import com.example.sportresults.feature_sport_activity.data.local.SportType
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.model.SportActivity
import com.example.sportresults.feature_sport_activity.domain.use_case.SportActivityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportActivityDetailViewModel @Inject constructor(
    private val sportActivityUseCases: SportActivityUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SportActivityDetailState())
    val state: State<SportActivityDetailState> = _state

    private val _distanceState = mutableStateOf<String>("")
    val distanceState: State<String> = _distanceState

    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

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

                checkPropertiesBeforeSave()
            }
            is SportActivityDetailEvent.SetStorageType -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    storageType = state.value.storageTypes.first { type -> type.type == event.type }
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
                checkPropertiesBeforeSave()
            }
            is SportActivityDetailEvent.SetHours -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    hours = event.value
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
                checkPropertiesBeforeSave()
            }
            is SportActivityDetailEvent.SetMinutes -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    minutes = event.value
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
                checkPropertiesBeforeSave()
            }
            is SportActivityDetailEvent.SetSeconds -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    seconds = event.value
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
                checkPropertiesBeforeSave()
            }
            is SportActivityDetailEvent.SetShowDurationDialog -> {
                _state.value = state.value.copy(
                    showDurationDialog = !state.value.showDurationDialog
                )
            }
            is SportActivityDetailEvent.SetDistanceString -> {
                _distanceState.value = event.value
            }
            is SportActivityDetailEvent.SetDistance -> {
                val floatValue = distanceState.value.replace(',','.').toFloatOrNull()
                var newSportActivity: SportActivity? = null

                if (floatValue != null) {
                    newSportActivity = state.value.sportActivity?.copy(
                        distance = floatValue
                    )
                } else {
                    newSportActivity = state.value.sportActivity?.copy(
                        distance = state.value.sportActivity!!.distance
                    )
                }
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )

                if(floatValue == null) {
                    _distanceState.value = ""
                } else
                    _distanceState.value = floatValue.toString()

                checkPropertiesBeforeSave()
            }
            is SportActivityDetailEvent.ClickSave -> {
                if (state.value.canSave) {
                    saveSportActivity()
                }
            }
            is SportActivityDetailEvent.SetPlace -> {
                val newSportActivity = state.value.sportActivity?.copy(
                    place = event.name
                )
                _state.value = state.value.copy(
                    sportActivity = newSportActivity
                )
                checkPropertiesBeforeSave()
            }
        }
    }

    private fun checkPropertiesBeforeSave() {
        state.value.sportActivity?.let { sportAtivity ->
            if (sportAtivity.sportType != null
                && (sportAtivity.hours > 0 || sportAtivity.minutes > 0 || sportAtivity.seconds > 0)
                && sportAtivity.storageType != null
                && sportAtivity.place.isNotBlank()
            ) {
                _state.value = state.value.copy(
                    canSave = true
                )
            } else {
                _state.value = state.value.copy(
                    canSave = false
                )
            }
        }
    }


    private fun loadSportTypes() {
        viewModelScope.launch {
            val response = sportActivityUseCases.getSportTypesUseCase()

            when (response) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        sportTypes = response.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        sportTypes = response.data ?: emptyList()
                    )
                    sendUiEvent(UiEvent.ShowSnackbar(response.message ?: "Neznámá chyba"))
                }
            }
        }
    }

    private fun loadStorageTypes() {
        viewModelScope.launch {
            val response = sportActivityUseCases.getStorageTypesUseCase(true)

            when (response) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        storageTypes = response.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(
                        storageTypes = response.data ?: emptyList()
                    )
                    sendUiEvent(UiEvent.ShowSnackbar(response.message ?: "Neznámá chyba"))
                }
            }
        }
    }

    private fun saveSportActivity() {
        viewModelScope.launch {
            state.value.sportActivity?.let { sportActivity ->
                val response = sportActivityUseCases.insertActivityUseCase(sportActivity)

                when (response) {
                    is Resource.Success -> {
                        sendUiEvent(UiEvent.Navigate(Screen.SportActivityMainScreen.route))
                    }
                    is Resource.Error -> {
                        sendUiEvent(UiEvent.ShowSnackbar(response.message ?: "Neznámá chyba"))
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}