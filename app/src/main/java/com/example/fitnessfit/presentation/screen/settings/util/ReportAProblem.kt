package com.example.fitnessfit.presentation.screen.settings.util

import android.content.Context
import android.content.Intent
import com.example.fitnessfit.R
import com.example.fitnessfit.util.Util

object ReportAProblem {
    fun  reportAProblem(context: Context){
        val email = "FitnessFit.Of.Contact@gmail.com"
        val subject = "Report a Problem"
        val gmailPackageName = "com.google.android.gm"

        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            `package` = gmailPackageName
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            Util.makeToast(context,R.string.settings_reportAProblemError)
        }
    }
}