package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportresults.core.util.Resource
import com.example.sportresults.core.util.UiEvent
import com.example.sportresults.feature_sport_activity.data.local.StorageType
import com.example.sportresults.feature_sport_activity.domain.use_case.SportActivityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SportActivityMainViewModel @Inject constructor(
    private val sportActivityUseCases: SportActivityUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SportActivityMainState())
    val state: State<SportActivityMainState> = _state

    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadStorageTypes()
        loadActivities()
    }

    fun onEvent(event: SportActivityMainEvent) {
        when (event) {
            is SportActivityMainEvent.ClickNewSportActivity -> {

            }
            is SportActivityMainEvent.SportActivityItemClick -> {

            }
            is SportActivityMainEvent.StorageTypeChange -> {
                _state.value = state.value.copy(
                    selectedStorageType = event.sorageType
                )
                loadActivities()
            }
        }
    }

    private fun loadActivities() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val response = sportActivityUseCases.getActivitiesUseCase(state.value.selectedStorageType)

            when (response) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        sportActivities = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    Timber.d(response.message)
                    _state.value = state.value.copy(
                        isLoading = false,
                        sportActivities = response.data ?: emptyList()
                    )

                    sendUiEvent(UiEvent.ShowSnackbar(response.message ?: "Neznámá chyba"))
                }
            }
        }
    }

    private fun loadStorageTypes() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                storageTypes = StorageType.getList()
            )
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}