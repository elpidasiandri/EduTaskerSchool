package com.example.edutasker.model

data class UpdateTaskByProfessorModel(
    val taskId: String,
    val taskDescription: String,
    val taskTitle: String,
    val taskDeadline: String,
    val progress: String,
)