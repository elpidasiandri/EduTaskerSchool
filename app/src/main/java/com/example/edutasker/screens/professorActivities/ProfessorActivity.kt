package com.example.edutasker.screens.professorActivities

import android.app.Activity
import android.content.Context
import android.content.Intent

class ProfessorActivity():Activity() {
    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, ProfessorActivity::class.java)
            context.startActivity(intent)
        }
    }
}