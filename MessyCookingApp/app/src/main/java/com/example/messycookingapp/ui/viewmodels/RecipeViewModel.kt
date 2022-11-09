package com.example.messycookingapp.ui.viewmodels

import com.example.messycookingapp.data.states.RecipeUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RecipeViewModel
{
    private val _uiState = MutableStateFlow(RecipeUIState())

    var uiState: StateFlow<RecipeUIState> = _uiState.asStateFlow()
}