package com.example.edutasker.model

data class TaskModel(
    val taskId: String,
    val subjectName: String,
    val description: String,
    val assignTo: List<String>,
    val assignByProfessor: String,
    val deadlineDate: String,
    val creationDate: String,
    val progress :TaskStatus
)

enum class TaskStatus {
    TODO, IN_PROGRESS, DONE, CLOSED
}
