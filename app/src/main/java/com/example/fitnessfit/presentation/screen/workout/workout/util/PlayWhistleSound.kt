package com.example.fitnessfit.presentation.screen.workout.workout.util

import android.content.Context
import android.media.MediaPlayer
import com.example.fitnessfit.R

object PlayWhistleSound {
    fun playWhistleSound(context: Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.referee_whistle)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
    }
}