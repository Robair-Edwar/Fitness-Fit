package com.example.fitnessfit.presentation.screen.workout.workout.util

import com.example.fitnessfit.R

object GetWorkoutDetails {

    fun getWorkoutDetails(workoutName: String): WorkoutDetails? {
        return when (workoutName) {
            "Wall Push-ups" -> WorkoutDetails(R.drawable.wall_push_ups, WorkoutType.TIME, duration = 60)
            "Arm Circles" -> WorkoutDetails(R.drawable.arm_circles, WorkoutType.TIME, duration = 60)
            "Bodyweight Squats" -> WorkoutDetails(R.drawable.bodyweight_squats, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Lunges" -> WorkoutDetails(R.drawable.lunges, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "High Knees" -> WorkoutDetails(R.drawable.high_knees, WorkoutType.TIME, duration = 60)
            "Reverse Lunges" -> WorkoutDetails(R.drawable.reverse_lunges, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Planks" -> WorkoutDetails(R.drawable.planks, WorkoutType.TIME, duration = 60)
            "Russian Twists" -> WorkoutDetails(R.drawable.russian_twists, WorkoutType.SETS, sets = 3, repsPerSet = 20)
            "Jumping Jacks" -> WorkoutDetails(R.drawable.jumping_jacks, WorkoutType.TIME, duration = 60)
            "Wall Sits" -> WorkoutDetails(R.drawable.wall_sits, WorkoutType.TIME, duration = 60)
            "Butt Kicks" -> WorkoutDetails(R.drawable.butt_kicks, WorkoutType.TIME, duration = 60)
            "Marching in Place" -> WorkoutDetails(R.drawable.marching_in_place, WorkoutType.TIME, duration = 60)
            "Step Touch" -> WorkoutDetails(R.drawable.step_touch, WorkoutType.TIME, duration = 60)
            "Jogging in Place" -> WorkoutDetails(R.drawable.jogging_in_place, WorkoutType.TIME, duration = 60)
            "Stretching" -> WorkoutDetails(R.drawable.stretching, WorkoutType.TIME, duration = 300)
            "Walking" -> WorkoutDetails(R.drawable.walking_lunges, WorkoutType.TIME, duration = 1800)
            "Leg Raises" -> WorkoutDetails(R.drawable.leg_raises, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Sit-ups" -> WorkoutDetails(R.drawable.sit_ups, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Mountain Climbers" -> WorkoutDetails(R.drawable.mountain_climbers, WorkoutType.TIME, duration = 60)
            "Side Planks" -> WorkoutDetails(R.drawable.side_planks, WorkoutType.TIME, duration = 60)
            "Walking Lunges" -> WorkoutDetails(R.drawable.walking_lunges, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Jump Squats" -> WorkoutDetails(R.drawable.jump_squats, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Bicycle Crunches" -> WorkoutDetails(R.drawable.bicycle_crunches, WorkoutType.SETS, sets = 3, repsPerSet = 20)
            "Burpees" -> WorkoutDetails(R.drawable.burpees, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Jump Lunges" -> WorkoutDetails(R.drawable.jump_lunges, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Skater Jumps" -> WorkoutDetails(R.drawable.skater_jumps, WorkoutType.SETS, sets = 3, repsPerSet = 20)
            "Plank Jacks" -> WorkoutDetails(R.drawable.plank_jacks, WorkoutType.TIME, duration = 60)
            "Flutter Kicks" -> WorkoutDetails(R.drawable.flutter_kicks, WorkoutType.SETS, sets = 3, repsPerSet = 20)
            "Inchworms" -> WorkoutDetails(R.drawable.inchworms, WorkoutType.SETS, sets = 3, repsPerSet = 10)
            "Bodyweight Dips" -> WorkoutDetails(R.drawable.bodyweight_dips, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Pike Push-ups" -> WorkoutDetails(R.drawable.pike_push_ups, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Reverse Crunches" -> WorkoutDetails(R.drawable.reverse_crunches, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Tuck Jumps" -> WorkoutDetails(R.drawable.tuck_jumps, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            "Hollow Holds" -> WorkoutDetails(R.drawable.hollow_holds, WorkoutType.TIME, duration = 60)
            "Jump Rope" -> WorkoutDetails(R.drawable.jump_rope, WorkoutType.TIME, duration = 60)
            "Dynamic Stretching" -> WorkoutDetails(R.drawable.stretching, WorkoutType.TIME, duration = 300)
            "Active Recovery" -> WorkoutDetails(R.drawable.recovery, WorkoutType.TIME, duration = 300)
            "Handstand Push-ups" -> WorkoutDetails(R.drawable.handstand_push_ups, WorkoutType.SETS, sets = 3, repsPerSet = 10)
            "Push-ups" -> WorkoutDetails(R.drawable.push_ups, WorkoutType.SETS, sets = 3, repsPerSet = 15)
            else -> null
        }
    }
}
