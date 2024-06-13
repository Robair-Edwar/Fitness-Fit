package com.example.fitnessfit.presentation.screen.workout.home_workouts

import android.util.Log
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
class HomeWorkoutsViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
): ViewModel() {

    private var _screenState = MutableStateFlow(HomeWorkoutsUiState())
    var screenState = _screenState.asStateFlow()

    init {
        fireStoreRepository.startListeningToUserDashboard()
        readUserPlan()
        readUserDashboard()
    }

    private fun readUserPlan() {
        viewModelScope.launch {
            _screenState.value = screenState.value.copy(loading = true)
            try {
                val data = fireStoreRepository.readUserPlan()
                _screenState.value = screenState.value.copy(
                    loading = false,
                    data = data,
                    error = -1
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _screenState.value = screenState.value.copy(
                    loading = false,
                    error = R.string.dashboard_error
                )
                println("Error: while getting user plan")
            }
        }
    }

    private fun readUserDashboard() {
        viewModelScope.launch {
            _screenState.value = screenState.value.copy(loading = true)
            try {
                fireStoreRepository.userDashboard.collect { result ->
                    result?.let {
                        _screenState.value = screenState.value.copy(
                            finished = result.finished,
                            remain = result.remain,
                            firstWeekProgress = result.firstWeekProgress,
                            secondWeekProgress = result.secondWeekProgress,
                            thirdWeekProgress = result.thirdWeekProgress,
                            fourthWeekProgress = result.fourthWeekProgress,
                            loading = false,
                            error = -1
                        )
                    } ?: run {
                        _screenState.value = screenState.value.copy(
                            loading = false,
                            error = R.string.dashboard_error
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _screenState.value = screenState.value.copy(
                    loading = false,
                    error = R.string.dashboard_error
                )
            }
        }
    }

    fun updateProgress(number: Int){
        viewModelScope.launch {
            try {
                if (number >= screenState.value.finished) {
                    fireStoreRepository.updateUserDashboardField(
                        "finished", number
                    )
                    fireStoreRepository.updateUserDashboardField(
                        "remain", 32 - number
                    )
                    if (number <= 8){
                        fireStoreRepository.updateUserDashboardField(
                            "firstWeekProgress", number
                        )
                    }else if (number <= 16){
                        fireStoreRepository.updateUserDashboardField(
                            "secondWeekProgress", number - 8
                        )
                    } else if (number <= 24){
                        fireStoreRepository.updateUserDashboardField(
                            "thirdWeekProgress", number - 16
                        )
                    } else {
                        fireStoreRepository.updateUserDashboardField(
                            "fourthWeekProgress", number - 24
                        )
                    }
                }
            }catch (e: Exception){
                e.printStackTrace()
                Log.d("Update Progress","Error while updating progrss")
            }
        }
    }
}
