package com.example.edutasker.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val taskId: String,
    val subjectName: String,
    val description: String,
    val assignBy: String,
    val deadlineDate: String,
    val creationDate: String,
    val assignTo: List<String>,
    val progress: String,
    val fileUri: String? = null
)