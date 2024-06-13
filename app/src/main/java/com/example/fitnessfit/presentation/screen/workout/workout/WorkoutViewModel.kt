package com.example.fitnessfit.presentation.screen.workout.workout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessfit.R
import com.example.fitnessfit.domain.repository.FireStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
): ViewModel() {

    private var finished = 0
    init {
        fireStoreRepository.startListeningToUserDashboard()
        readUserDashboard()
    }

    private fun readUserDashboard() {
        viewModelScope.launch {
            try {
                fireStoreRepository.userDashboard.collect { result ->
                    result?.let {
                        finished = it.finished
                    } ?: run {

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateProgress(number: Int){
        viewModelScope.launch {
            try {
                if (number >= finished) {
                    fireStoreRepository.updateUserDashboardField(
                        "finished", number
                    )
                    fireStoreRepository.updateUserDashboardField(
                        "remain", 32 - number
                    )
                }
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
            }catch (e: Exception){
                e.printStackTrace()
                Log.d("Update Progress","Error while updating progrss")
            }
        }
    }
}