package com.example.fitnessfit.presentation.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fireStoreRepository.startListeningToUserDashboard()
        readUserDashboard()
    }

    private fun readUserDashboard(){
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                loading = true
            )
            try {
                fireStoreRepository.userDashboard.collect { result ->
                    result?.let {
                        _uiState.value = uiState.value.copy(
                            name = result.name,
                            age = result.age,
                            gender = if (result.gender == 0) R.string.dashboard_male else R.string.dashboard_female,
                            height = result.height,
                            weight = result.weight,
                            finished = result.finished,
                            remain = result.remain,
                            firstWeekProgress = result.firstWeekProgress,
                            secondWeekProgress = result.secondWeekProgress,
                            thirdWeekProgress = result.thirdWeekProgress,
                            fourthWeekProgress = result.fourthWeekProgress,
                            error = -1  // Reset error state if data is successfully loaded
                        )
                    } ?: run {
                        _uiState.value = uiState.value.copy(
                            loading = false,
                            error = R.string.dashboard_error
                        )
                    }
                    _uiState.value = uiState.value.copy(
                        loading = false
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = uiState.value.copy(
                    loading = false,
                    error = R.string.dashboard_error
                )
            }
        }
    }
}
