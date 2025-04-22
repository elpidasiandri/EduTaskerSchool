package com.example.edutasker.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val taskId: String,
    val subjectName: String,
    val description: String,
    val assignBy: String,
)