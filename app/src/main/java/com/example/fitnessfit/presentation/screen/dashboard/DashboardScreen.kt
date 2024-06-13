package com.example.fitnessfit.presentation.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.Share
import com.example.fitnessfit.presentation.common.bars.BottomAppBar
import com.example.fitnessfit.presentation.common.bars.BottomBarScreens
import com.example.fitnessfit.presentation.common.bars.TopAppBar
import com.example.fitnessfit.presentation.common.constants.Dimens
import com.example.fitnessfit.presentation.screen.dashboard.component.Days
import com.example.fitnessfit.presentation.screen.dashboard.component.Progress
import com.example.fitnessfit.presentation.screen.dashboard.component.UserInfo
import com.example.fitnessfit.presentation.common.ErrorScreen
import com.example.fitnessfit.presentation.common.LoadingScreen

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen({},{},{})
}

@Composable
fun DashboardScreen(
    onWorkoutClick: () -> Unit,
    onMealsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    dashboardViewModel: DashboardViewModel = hiltViewModel<DashboardViewModel>()
) {
    val uiState by dashboardViewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopAppBar(text = stringResource(id = R.string.dashboard_profile), TextAlign.Start) },
        bottomBar = {
            BottomAppBar(
                selectedBottomBarScreens = BottomBarScreens.Dashboard,
                onWorkoutClick = onWorkoutClick,
                onMealsClick = onMealsClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { innerPadding ->
        when {
            uiState.loading -> {
                LoadingScreen(modifier = Modifier.padding(innerPadding))
            }
            uiState.error != -1 -> {
                ErrorScreen(errorMessage = stringResource(id = uiState.error), modifier = Modifier.padding(innerPadding))
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(Dimens.Small4)
                        .background(MaterialTheme.colorScheme.background)
                        .verticalScroll(rememberScrollState())
                ) {
                    UserInfo(
                        name = uiState.name,
                        gender = stringResource(id = uiState.gender),
                        age = uiState.age.toString(),
                        height = uiState.height.toString(),
                        weight = uiState.weight.toString()
                    )
                    Spacer(modifier = Modifier.height(Dimens.Small4))
                    Days(
                        finished = uiState.finished.toString(),
                        remaining = uiState.remain.toString()
                    )
                    Spacer(modifier = Modifier.height(Dimens.Small4))
                    Share(stringResource(id = R.string.dashboard_share)) {
                        Progress(
                            week1 = uiState.firstWeekProgress,
                            week2 = uiState.secondWeekProgress,
                            week3 = uiState.thirdWeekProgress,
                            week4 = uiState.fourthWeekProgress
                        )
                    }
                    Spacer(modifier = Modifier.height(Dimens.Small4))
                }
            }
        }
    }
}
