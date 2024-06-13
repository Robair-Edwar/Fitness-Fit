package com.example.fitnessfit.presentation.common.bars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitnessfit.presentation.common.constants.Dimens

@Preview
@Composable
fun BottomAppBarPreview() {
    BottomAppBar(BottomBarScreens.Settings, {}, {}, {}, {})
}

@Composable
fun BottomAppBar(
    selectedBottomBarScreens: BottomBarScreens,
    onWorkoutClick: () -> Unit = {},
    onMealsClick: () -> Unit = {},
    onDashboardClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(topStart = Dimens.Medium3, topEnd = Dimens.Medium3))
            .background(MaterialTheme.colorScheme.primary)
    ) {
        BottomAppBar(
            containerColor = Color.Transparent, // Make BottomAppBar background transparent
            modifier = Modifier.fillMaxWidth()
        ) {
            NavigationBarItem(
                selected = selectedBottomBarScreens == BottomBarScreens.Workouts,
                onClick = onWorkoutClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Workout",
                        tint = if (selectedBottomBarScreens == BottomBarScreens.Workouts) Color.Gray else Color.White
                    )
                },
            )
            NavigationBarItem(
                selected = selectedBottomBarScreens == BottomBarScreens.Meals,
                onClick = onMealsClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Meals",
                        tint = if (selectedBottomBarScreens == BottomBarScreens.Meals) Color.Gray else Color.White
                    )
                },
            )
            NavigationBarItem(
                selected = selectedBottomBarScreens == BottomBarScreens.Dashboard,
                onClick = onDashboardClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Dashboard",
                        tint = if (selectedBottomBarScreens == BottomBarScreens.Dashboard) Color.Gray else Color.White
                    )
                },
            )
            NavigationBarItem(
                selected = selectedBottomBarScreens == BottomBarScreens.Settings,
                onClick = onSettingsClick,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = if (selectedBottomBarScreens == BottomBarScreens.Settings) Color.Gray else Color.White
                    )
                },
            )
        }
    }
}
