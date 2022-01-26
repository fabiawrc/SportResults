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
    val stateSport: State<SportActivityDetailState> = _state

    init {
        loadSportTypes()
        loadStorageTypes()
    }


    private fun loadSportTypes(){
        viewModelScope.launch {
            _state.value= stateSport.value.copy(
                sportTypes = SportType.getList()
            )
        }
    }

    private fun loadStorageTypes(){
        viewModelScope.launch {
            _state.value= stateSport.value.copy(
                storageTypes = StorageType.getList()
            )
        }
    }

}