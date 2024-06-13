package com.example.fitnessfit.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.fitnessfit.presentation.screen.auth.login.LoginScreen
import com.example.fitnessfit.presentation.screen.auth.register.RegisterScreen
import com.example.fitnessfit.presentation.screen.auth.register.RegisterViewModel
import com.example.fitnessfit.presentation.screen.dashboard.DashboardScreen
import com.example.fitnessfit.presentation.screen.meals.meal_screen.MealScreen
import com.example.fitnessfit.presentation.screen.meals.meals_categories_screen.MealsCategoriesScreen
import com.example.fitnessfit.presentation.screen.meals.meals_screen.MealsScreen
import com.example.fitnessfit.presentation.screen.on_boarding.OnboardingViewModel
import com.example.fitnessfit.presentation.screen.on_boarding.eighth_onboarding.EighthOnboarding
import com.example.fitnessfit.presentation.screen.on_boarding.fifth_onboarding.FifthOnBoardingScreen
import com.example.fitnessfit.presentation.screen.on_boarding.first_onboarding.FirstOnBoardingScreen
import com.example.fitnessfit.presentation.screen.on_boarding.fourth_onboarding.FourthOnBoardingScreen
import com.example.fitnessfit.presentation.screen.on_boarding.second_onboarding.SecondOnBoardingScreen
import com.example.fitnessfit.presentation.screen.on_boarding.seventh_onboarding.SeventhOnBoardingScreen
import com.example.fitnessfit.presentation.screen.on_boarding.sixth_onboarding.SixthOnBoardingScreen
import com.example.fitnessfit.presentation.screen.on_boarding.third_onboarding.ThirdOnBoardingScreen
import com.example.fitnessfit.presentation.screen.settings.SettingsScreen
import com.example.fitnessfit.presentation.screen.settings.SettingsViewModel
import com.example.fitnessfit.presentation.screen.splash.AnimatedSplashScreen
import com.example.fitnessfit.presentation.screen.workout.home_workouts.HomeWorkoutsScreen
import com.example.fitnessfit.presentation.screen.workout.workout.WorkoutScreen1
import com.example.fitnessfit.presentation.screen.workout.workout.WorkoutScreen2
import com.example.fitnessfit.presentation.screen.workout.workout.WorkoutScreen3
import com.example.fitnessfit.presentation.screen.workout.workout.WorkoutScreen4
import com.example.fitnessfit.presentation.screen.workout.workout.WorkoutScreen5

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    onboardingViewModel: OnboardingViewModel = viewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>(),
    settingsViewModel: SettingsViewModel = hiltViewModel<SettingsViewModel>(),
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(
            route = Screen.Splash.route
        ) {
            AnimatedSplashScreen(onSplashEnd = {
                if (registerViewModel.isSignIn()) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                } else {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            })
        }

        navigation(
            route = Screen.Onboarding.route,
            startDestination = Screen.Onboarding1.route
        ) {
            composable(
                route = Screen.Onboarding1.route
            ) {
                FirstOnBoardingScreen(
                    onHaveAccountClick = { navController.navigate(Screen.Auth.route) },
                    onNextButtonClick = { navController.navigate(Screen.Onboarding2.route) },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding2.route
            ) {
                SecondOnBoardingScreen(
                    onNextButtonClick = {
                        navController.navigate(Screen.Onboarding3.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding3.route
            ) {
                ThirdOnBoardingScreen(
                    onNextButtonClick = {
                        navController.navigate(Screen.Onboarding4.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding4.route
            ) {
                FourthOnBoardingScreen(
                    onNextButtonClick = {
                        navController.navigate(Screen.Onboarding5.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding5.route
            ) {
                FifthOnBoardingScreen(
                    onNextButtonClick = {
                        navController.navigate(Screen.Onboarding6.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding6.route
            ) {
                SixthOnBoardingScreen(
                    onNextButtonClick = {
                        navController.navigate(Screen.Onboarding7.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding7.route
            ) {
                SeventhOnBoardingScreen(
                    onNextButtonClick = {
                        navController.navigate(Screen.Onboarding8.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }

            composable(
                route = Screen.Onboarding8.route
            ) {
                EighthOnboarding(
                    onFinishButtonClick = {
                        navController.navigate(Screen.SignUp.route)
                    },
                    onBackButtonClick = {
                        navController.navigateUp()
                    },
                    onboardingViewModel = onboardingViewModel
                )
            }
        }

        navigation(
            route = Screen.Auth.route,
            startDestination = Screen.Login.route
        ) {
            composable(
                route = Screen.Login.route
            ) {
                LoginScreen(
                    onRegisterClick = { navController.navigateUp() },
                    onLogInClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        composable(
            route = Screen.SignUp.route
        ) {
            RegisterScreen(
                onRegisterClick = {
                    onboardingViewModel.clearUserData()
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                    registerViewModel.clearUiState()
                },
                onBackButtonClick = {
                    navController.navigateUp()
                    registerViewModel.clearUiState()
                },
                registerViewModel = registerViewModel
            )
        }


        navigation(
            route = Screen.Home.route,
            startDestination = Screen.HomeWorkout.route
        ) {
            composable(
                route = Screen.HomeWorkout.route
            ) {
                HomeWorkoutsScreen(
                    onMealsClick = {
                        navController.navigate(Screen.Meals.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                        }
                    },
                    onDashboardClick = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                        }
                    },
                    onSettingsClick = {
                        navController.navigate(Screen.Settings.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = true
                            }
                        }
                    },
                    onDayWorkoutsClick = { workouts, number ->
                        val serializedWorkouts = workouts.joinToString(",")
                        navController.navigate(Screen.Workout1.createRoute(serializedWorkouts, number))
                    }
                )
            }

            composable(
                route = Screen.Workout1.route,
                arguments = listOf(
                    navArgument("workouts") { type = NavType.StringType },
                    navArgument("number") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val serializedWorkouts = backStackEntry.arguments?.getString("workouts")
                val number = backStackEntry.arguments?.getInt("number") ?: 1
                val workouts = serializedWorkouts?.split(",")?.map { it } ?: emptyList()
                val deSerializedWorkouts = workouts.joinToString(",")

                WorkoutScreen1(workouts = workouts, onNextClick = {
                    navController.navigate(Screen.Workout2.createRoute(deSerializedWorkouts, number))
                }) {
                    navController.navigate(Screen.Workout2.createRoute(deSerializedWorkouts, number))
                }
            }

            composable(
                route = Screen.Workout2.route,
                arguments = listOf(
                    navArgument("workouts") { type = NavType.StringType },
                    navArgument("number") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val serializedWorkouts = backStackEntry.arguments?.getString("workouts")
                val number = backStackEntry.arguments?.getInt("number") ?: 2
                val workouts = serializedWorkouts?.split(",")?.map { it } ?: emptyList()
                val deSerializedWorkouts = workouts.joinToString(",")

                WorkoutScreen2(workouts = workouts, onNextClick = {
                    navController.navigate(Screen.Workout3.createRoute(deSerializedWorkouts, number))
                }) {
                    navController.navigate(Screen.Workout3.createRoute(deSerializedWorkouts, number)) {
                        popUpTo(Screen.Workout2.route) {
                            inclusive = true
                        }
                    }
                }
            }

            composable(
                route = Screen.Workout3.route,
                arguments = listOf(
                    navArgument("workouts") { type = NavType.StringType },
                    navArgument("number") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val serializedWorkouts = backStackEntry.arguments?.getString("workouts")
                val number = backStackEntry.arguments?.getInt("number") ?: 3
                val workouts = serializedWorkouts?.split(",")?.map { it } ?: emptyList()
                val deSerializedWorkouts = workouts.joinToString(",")

                WorkoutScreen3(workouts = workouts, onNextClick = {
                    navController.navigate(Screen.Workout4.createRoute(deSerializedWorkouts, number)) {
                        popUpTo(Screen.Workout3.route) {
                            inclusive = true
                        }
                    }
                }) {
                    navController.navigate(Screen.Workout4.createRoute(deSerializedWorkouts, number))
                }
            }

            composable(
                route = Screen.Workout4.route,
                arguments = listOf(
                    navArgument("workouts") { type = NavType.StringType },
                    navArgument("number") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val serializedWorkouts = backStackEntry.arguments?.getString("workouts")
                val number = backStackEntry.arguments?.getInt("number") ?: 4
                val workouts = serializedWorkouts?.split(",")?.map { it } ?: emptyList()
                val deSerializedWorkouts = workouts.joinToString(",")

                WorkoutScreen4(workouts = workouts, onNextClick = {
                    navController.navigate(Screen.Workout5.createRoute(deSerializedWorkouts, number)) {
                        popUpTo(Screen.Workout4.route) {
                            inclusive = true
                        }
                    }
                }) {
                    navController.navigate(Screen.Workout5.createRoute(deSerializedWorkouts, number))
                }
            }

            composable(
                route = Screen.Workout5.route,
                arguments = listOf(
                    navArgument("workouts") { type = NavType.StringType },
                    navArgument("number") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val serializedWorkouts = backStackEntry.arguments?.getString("workouts")
                val number = backStackEntry.arguments?.getInt("number") ?: 5
                val workouts = serializedWorkouts?.split(",")?.map { it } ?: emptyList()

                WorkoutScreen5(
                    workouts = workouts,
                    onNextClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = false
                            }
                        }
                    },
                    onSkipClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) {
                                inclusive = false
                            }
                        }
                    },
                    number = number
                )
            }
        }

        navigation(
            route = Screen.Meals.route,
            startDestination = Screen.MealCategories.route
        ) {
            composable(
                route = Screen.MealCategories.route
            ) {
                MealsCategoriesScreen(
                    onCategoryClick = { category ->
                        navController.navigate(Screen.CategoryMeals.createRoute(category))
                    },
                    onWorkoutOutClick = {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Meals.route) {
                                inclusive = true
                            }
                        }
                    },
                    onSettingsClick = {
                        navController.navigate(Screen.Settings.route) {
                            popUpTo(Screen.Meals.route) { inclusive = true }
                        }
                    },
                    onDashboardClick = {
                        navController.navigate(Screen.Dashboard.route) {
                            popUpTo(Screen.Meals.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(
                route = Screen.CategoryMeals.route,
                arguments = listOf(navArgument("category") { type = NavType.StringType })
            ) { backStackEntry ->
                val category = backStackEntry.arguments?.getString("category") ?: ""
                MealsScreen(category) { mealId ->
                    navController.navigate(Screen.Meal.createRoute(mealId))
                }
            }

            composable(
                route = Screen.Meal.route,
                arguments = listOf(navArgument("mealId") { type = NavType.IntType })
            ) { backStackEntry ->
                val mealId = backStackEntry.arguments?.getInt("mealId") ?: 0
                MealScreen(mealId)
            }
        }

        composable(
            route = Screen.Dashboard.route
        ) {
            DashboardScreen(
                onWorkoutClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Dashboard.route) {
                            inclusive = true
                        }
                    }
                },
                onMealsClick = {
                    navController.navigate(Screen.Meals.route) {
                        popUpTo(Screen.Dashboard.route) {
                            inclusive = true
                        }
                    }
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route) {
                        popUpTo(Screen.Dashboard.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.Settings.route
        ) {
            SettingsScreen(
                onMealsClick = {
                    navController.navigate(Screen.Meals.route) {
                        popUpTo(Screen.Settings.route) { inclusive = true }
                    }
                },
                onWorkoutClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Settings.route) { inclusive = true }
                    }
                },
                onLogoutClick = {
                    onboardingViewModel.clearUserData()
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Settings.route) {
                            inclusive = true
                        }
                    }
                },
                onDashboardClick = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Settings.route) {
                            inclusive = true
                        }
                    }
                },
                settingsScreenViewModel = settingsViewModel
            )
        }

    }
}
