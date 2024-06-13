package com.example.fitnessfit.presentation.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash_screen")
    object Auth: Screen("auth_screen")
    object Onboarding: Screen("onboarding_screen")
    object Home: Screen("home_screen")
    object SignUp: Screen("signup_screen")
    object Login: Screen("login_screen")
    object HomeWorkout: Screen("home_workout_screen")
    object Workout1 : Screen("workout1_screen/{workouts}/{number}") {
        fun createRoute(workouts: String, number: Int): String {
            return "workout1_screen/$workouts/$number"
        }
    }
    object Workout2 : Screen("workout2_screen/{workouts}/{number}") {
        fun createRoute(workouts: String, number: Int): String {
            return "workout2_screen/$workouts/$number"
        }
    }
    object Workout3 : Screen("workout3_screen/{workouts}/{number}") {
        fun createRoute(workouts: String, number: Int): String {
            return "workout3_screen/$workouts/$number"
        }
    }
    object Workout4 : Screen("workout4_screen/{workouts}/{number}") {
        fun createRoute(workouts: String, number: Int): String {
            return "workout4_screen/$workouts/$number"
        }
    }
    object Workout5 : Screen("workout5_screen/{workouts}/{number}") {
        fun createRoute(workouts: String, number: Int): String {
            return "workout5_screen/$workouts/$number"
        }
    }
    object Meals: Screen("meals_screen")
    object MealCategories: Screen("mealCategories_screen")
    object CategoryMeals : Screen("categoryMeals/{category}") {
        fun createRoute(category: String) = "categoryMeals/$category"
    }
    object Meal : Screen("meal/{mealId}") {
        fun createRoute(mealId: Int) = "meal/$mealId"
    }
    object Dashboard: Screen("dashboard_screen")
    object Settings: Screen("settings_screen")
    object Onboarding1: Screen("onboarding1_screen")
    object Onboarding2: Screen("onboarding2_screen")
    object Onboarding3: Screen("onboarding3_screen")
    object Onboarding4: Screen("onboarding4_screen")
    object Onboarding5: Screen("onboarding5_screen")
    object Onboarding6: Screen("onboarding6_screen")
    object Onboarding7: Screen("onboarding7_screen")
    object Onboarding8: Screen("onboarding8_screen")

}
