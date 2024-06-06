package com.example.goatdatabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GoatViewModel(private val dao: GoatDao): ViewModel() {
    private val _state = MutableStateFlow(GoatState())
    private val _sortType = MutableStateFlow(SortType.NAME)
    private val _selectedGoat = MutableStateFlow<Goat?>(null)
    val selectedGoat: StateFlow<Goat?> = _selectedGoat.asStateFlow()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _goats = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.NAME -> dao.getGoatsOrderedByName()
                SortType.BREED -> dao.getGoatsOrderedByBreed()
                SortType.AGE -> dao.getGoatsOrderedByAge()
                SortType.GENDER -> dao.getGoatsOrderedByGender()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _sortType, _goats) { state, sortType, goats ->
        state.copy(goats = goats, sortType = sortType) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), GoatState())


    fun selectGoat(goat: Goat?) {
        _selectedGoat.value = goat
    }

    fun updateGoat(updatedGoat: Goat) {
        viewModelScope.launch {
            dao.insertGoat(updatedGoat) // Assuming you are using the @Insert(onConflict = REPLACE) strategy
            _selectedGoat.value = null // Optionally clear the selected goat after update
        }
    }



    fun onEvent(event: GoatEvent) {
        when(event) {
            is GoatEvent.DeleteGoat -> {
                viewModelScope.launch { dao.deleteGoat(event.goat) }
            }
            GoatEvent.HideDialog -> {
                _state.update { it.copy(isAddingGoat = false) }
            }
            GoatEvent.SaveGoat -> {
                val goatName = state.value.goatName
                val goatAge = state.value.goatAge
                val goatBreed = state.value.goatBreed
                val goatInfo = state.value.goatInfo
                val goatGender = state.value.goatGender

                if (goatName.isBlank() || goatAge.isBlank() || goatBreed.isBlank()) {
                    return
                }

                val goat = Goat(goatName = goatName, goatAge = goatAge, goatBreed = goatBreed, goatInfo = goatInfo, goatGender = goatGender)
                viewModelScope.launch { dao.insertGoat(goat) }
                _state.update{it.copy(isAddingGoat = false,
                    goatName = "",
                    goatInfo = "",
                    goatBreed = "",
                    goatAge = "",
                    goatGender = ""
                )}

            }
            is GoatEvent.SetGoatAge -> {
                _state.update {it.copy(goatAge = event.goatAge)}
            }
            is GoatEvent.SetGoatBreed -> {
                _state.update{it.copy(goatBreed = event.goatBreed)}
            }
            is GoatEvent.SetGoatInfo -> {
                _state.update { it.copy(goatInfo = event.goatInfo) }
            }
            is GoatEvent.SetGoatName -> {
                _state.update { it.copy(goatName = event.goatName) }
            }
            GoatEvent.ShowDialog -> {
                _state.update{it.copy(isAddingGoat = true)}
            }

            is GoatEvent.SortGoats -> {
                _sortType.value = event.sortType
            }

            is GoatEvent.SetGoatGender -> {
                _state.update {it.copy(goatGender = event.goatGender)}
            }

            GoatEvent.HideNewDialog -> {
                _state.update{it.copy(isUpdatingGoat = false)}
            }
            GoatEvent.ShowNewDialog -> {
                _state.update{it.copy(isUpdatingGoat = true)}
            }
        }
    }
}