package com.example.edutasker.entities.relations

import androidx.room.Entity

@Entity(primaryKeys = ["taskId", "studentId"])
data class TaskStudentCrossRef(
    val taskId: String,
    val studentId: String
)
