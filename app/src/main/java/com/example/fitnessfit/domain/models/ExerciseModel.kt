package com.example.fitnessfit.domain.models

import android.content.Context
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer

class ExerciseModel(context: Context) {

    private val interpreter: Interpreter
    private val inputShape: IntArray
    private val outputShape: IntArray
    private val allWorkouts: List<String>
    private val outputColumns: List<String>

    init {
        val model = FileUtil.loadMappedFile(context, "exercise_ai_model.tflite")
        interpreter = Interpreter(model)
        inputShape = interpreter.getInputTensor(0).shape()
        outputShape = interpreter.getOutputTensor(0).shape()

        allWorkouts = listOf(
            "Wall Push-ups",
            "Arm Circles",
            "Bodyweight Squats",
            "Lunges",
            "High Knees",
            "Reverse Lunges",
            "Planks",
            "Russian Twists",
            "Jumping Jacks",
            "Wall Sits",
            "Butt Kicks",
            "Marching in Place",
            "Step Touch",
            "Jogging in Place",
            "Stretching",
            "Walking",
            "Leg Raises",
            "Sit-ups",
            "Mountain Climbers",
            "Side Planks",
            "Walking Lunges",
            "Jump Squats",
            "Bicycle Crunches",
            "Burpees",
            "Jump Lunges",
            "Skater Jumps",
            "Plank Jacks",
            "Flutter Kicks",
            "Inchworms",
            "Bodyweight Dips",
            "Pike Push-ups",
            "Reverse Crunches",
            "Tuck Jumps",
            "Hollow Holds",
            "Jump Rope",
            "Dynamic Stretching",
            "Active Recovery",
            "Handstand Push-ups",
            "Push-ups"
        )

        
        outputColumns = mutableListOf<String>().apply {
            for (day in 1..24) {
                for (workout in allWorkouts) {
                    add("${day}_${workout}")
                }
            }
        }
    }

    fun predict(inputData: FloatArray): List<List<String>> {
        val inputBuffer = TensorBuffer.createFixedSize(inputShape, DataType.FLOAT32)
        inputBuffer.loadArray(inputData)

        val outputBuffer = TensorBuffer.createFixedSize(outputShape, DataType.FLOAT32)
        interpreter.run(inputBuffer.buffer, outputBuffer.buffer.rewind())

        return mapPredictionsToExercises(outputBuffer.floatArray)
    }

    private fun mapPredictionsToExercises(predictions: FloatArray): List<List<String>> {
        val exercisesByDay = mutableListOf<List<String>>()

        for (day in 1..24) {
            val dayExercises = mutableListOf<Pair<String, Float>>()

            for (workout in allWorkouts) {
                val columnIndex = outputColumns.indexOf("${day}_${workout}")
                dayExercises.add(Pair(workout, predictions[columnIndex]))
            }

            // Sort workouts by prediction score in descending order and select the top 5
            dayExercises.sortByDescending { it.second }
            val top5Exercises = dayExercises.take(5).map { it.first }

            exercisesByDay.add(top5Exercises)
        }
        return exercisesByDay
    }
}
