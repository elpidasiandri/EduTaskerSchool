package com.example.edutasker.model

data class TaskModel(
    val taskId: String,
    val taskTitle: String,
    val subjectName: String,
    val description: String,
    val assignTo: List<String>,
    val assignByProfessor: String,
    val deadlineDate: String,
    val creationDate: String,
    val progress: TaskStatus,
)

enum class TaskStatus(key: String) {
    TODO("TODO"), IN_PROGRESS("IN_PROGRESS"), DONE("DONE"), CLOSED("CLOSED")
}
