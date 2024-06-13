package com.example.fitnessfit.presentation.screen.workout.home_workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fitnessfit.R
import com.example.fitnessfit.presentation.common.ErrorScreen
import com.example.fitnessfit.presentation.common.LoadingScreen
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.domain.models.ExercisePrediction
import com.example.fitnessfit.presentation.common.bars.BottomAppBar
import com.example.fitnessfit.presentation.common.bars.BottomBarScreens
import com.example.fitnessfit.presentation.common.bars.TopAppBar
import com.example.fitnessfit.presentation.screen.workout.home_workouts.component.HomeWorkoutsScreenContent
import org.tensorflow.lite.schema.Padding

@Preview
@Composable
fun HomeWorkoutsScreenPreview() {
}

@Composable
fun HomeWorkoutsScreen(
    onMealsClick: () -> Unit,
    onDashboardClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onDayWorkoutsClick: (workouts: List<String>,number: Int) -> Unit,
    homeWorkoutsViewModel: HomeWorkoutsViewModel = hiltViewModel<HomeWorkoutsViewModel>()
) {
    val screenState by homeWorkoutsViewModel.screenState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(text = stringResource(id = R.string.homeWorkoutScreen_myPlan))
        },
        bottomBar = {
            BottomAppBar(
                selectedBottomBarScreens = BottomBarScreens.Workouts,
                onDashboardClick = { onDashboardClick() },
                onMealsClick = { onMealsClick() },
                onSettingsClick = { onSettingsClick() }
            )
        }
    ) { innerPadding ->
        when {
            screenState.loading -> LoadingScreen(modifier = Modifier.padding(innerPadding))
            screenState.error != -1 -> ErrorScreen(
                errorMessage = stringResource(screenState.error),
                modifier = Modifier.padding(innerPadding)
            )

            screenState.data != null -> HomeWorkoutsScreenContent(
                finished = screenState.finished,
                onClickDay = { number ->
                    when (number) {
                        1 -> onDayWorkoutsClick(screenState.data!!.day1, 1)
                        2 -> onDayWorkoutsClick(screenState.data!!.day2, 2)
                        3 -> onDayWorkoutsClick(screenState.data!!.day3, 3)
                        4 -> onDayWorkoutsClick(screenState.data!!.day4, 5)
                        5 -> onDayWorkoutsClick(screenState.data!!.day5, 6)
                        6 -> onDayWorkoutsClick(screenState.data!!.day6, 7)
                        7 -> onDayWorkoutsClick(screenState.data!!.day7, 9)
                        8 -> onDayWorkoutsClick(screenState.data!!.day8, 10)
                        9 -> onDayWorkoutsClick(screenState.data!!.day9, 11)
                        10 -> onDayWorkoutsClick(screenState.data!!.day10, 13)
                        11 -> onDayWorkoutsClick(screenState.data!!.day11, 14)
                        12 -> onDayWorkoutsClick(screenState.data!!.day12, 15)
                        13 -> onDayWorkoutsClick(screenState.data!!.day13, 17)
                        14 -> onDayWorkoutsClick(screenState.data!!.day14, 18)
                        15 -> onDayWorkoutsClick(screenState.data!!.day15, 19)
                        16 -> onDayWorkoutsClick(screenState.data!!.day16, 21)
                        17 -> onDayWorkoutsClick(screenState.data!!.day17, 22)
                        18 -> onDayWorkoutsClick(screenState.data!!.day18, 23)
                        19 -> onDayWorkoutsClick(screenState.data!!.day19, 25)
                        20 -> onDayWorkoutsClick(screenState.data!!.day20, 26)
                        21 -> onDayWorkoutsClick(screenState.data!!.day21, 27)
                        22 -> onDayWorkoutsClick(screenState.data!!.day22, 29)
                        23 -> onDayWorkoutsClick(screenState.data!!.day23, 30)
                        24 -> onDayWorkoutsClick(screenState.data!!.day24, 31)
                    }
                },
                onClickRest = { number ->
                    homeWorkoutsViewModel.updateProgress(number)
                },
                padding = innerPadding
            )
        }
    }
}