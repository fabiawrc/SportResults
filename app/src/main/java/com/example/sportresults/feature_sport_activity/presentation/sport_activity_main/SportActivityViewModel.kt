package com.example.sportresults.feature_sport_activity.presentation.sport_activity_main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportresults.core.util.Resource
import com.example.sportresults.feature_sport_activity.domain.use_case.SportActivityUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SportActivityViewModel @Inject constructor(
    private val sportActivityUseCases: SportActivityUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SportActivityMainState())
    val stateSport: State<SportActivityMainState> = _state

    init {
        loadActivities()
    }

    fun onEvent(eventSport: SportActivityMainEvent) {
        when (eventSport) {
            is SportActivityMainEvent.ClickNewSportActivity -> {

            }
            is SportActivityMainEvent.SportActivityItemClick -> {

            }
            is SportActivityMainEvent.StorageTypeChange -> {

            }
        }
    }

    private fun loadActivities() {
        viewModelScope.launch {
            _state.value = stateSport.value.copy(
                isLoading = true
            )
            val response = sportActivityUseCases.getActivitiesUseCase()

            when (response) {
                is Resource.Success -> {
                    _state.value = stateSport.value.copy(
                        sportActivities = response.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    Timber.d(response.message)
                    _state.value = stateSport.value.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}