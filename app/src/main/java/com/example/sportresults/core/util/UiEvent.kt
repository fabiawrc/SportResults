package com.example.sportresults.core.util

sealed class UiEvent: Event() {
    data class ShowSnackbar(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}
