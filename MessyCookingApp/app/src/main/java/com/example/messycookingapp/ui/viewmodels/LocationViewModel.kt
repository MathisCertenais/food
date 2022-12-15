package com.example.messycookingapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.messycookingapp.data.states.repository.LocationRepository
import com.example.messycookingapp.data.states.states.LocationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationViewModel @Inject constructor(private val locationRepository: LocationRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LocationUiState(0.0f,0.0f))

    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    private fun getRecipe(){
        viewModelScope.launch {
            val locationResult = locationRepository.getLocation()
            _uiState.emit(LocationUiState(latitude = locationResult.latitude, longitude = locationResult.longitude))
        }
    }
}