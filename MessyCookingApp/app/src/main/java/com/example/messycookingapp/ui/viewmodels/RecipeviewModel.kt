package com.example.messycookingapp.ui.viewmodels

import android.text.Spannable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.messycookingapp.LanOrganizerApplication
import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.repository.RecipeRepository
import com.example.messycookingapp.data.states.states.RecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Converter
import javax.inject.Inject

@HiltViewModel
class RecipeviewModel @Inject constructor(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(listOf<RecipeUIState>())

    val uiState : StateFlow<List<RecipeUIState>> = _uiState.asStateFlow()

    private fun getRecipe(){
        viewModelScope.launch {
            try{
                val listRecipe = recipeRepository.getRecipe()
                _uiState.emit(listRecipe.map {
                    RecipeUIState(it.title)
                })
            }catch(e: Exception){

            }
        }

    }

 //   fun getterGetRecipe() {
  //      return getRecipe()
   // }


}