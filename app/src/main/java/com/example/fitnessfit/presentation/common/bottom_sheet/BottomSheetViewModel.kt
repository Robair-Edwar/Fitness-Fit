package com.example.fitnessfit.presentation.common.bottom_sheet

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BottomSheetViewModel: ViewModel() {
    var isSheetOpen by mutableStateOf(false)
        private set

    fun openSheet(){
        isSheetOpen = true
    }

    fun closeSheet(){
        isSheetOpen = false
    }
}