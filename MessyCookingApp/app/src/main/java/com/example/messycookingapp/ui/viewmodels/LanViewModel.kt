package com.example.messycookingapp.ui.viewmodels

import com.example.messycookingapp.data.states.states.RecipeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LanViewModel
{
    private val _uiState = MutableStateFlow(RecipeUIState())

    var uiState: StateFlow<RecipeUIState> = _uiState.asStateFlow()
}