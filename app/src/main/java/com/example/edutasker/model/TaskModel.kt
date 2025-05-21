package com.example.edutasker.model

data class TaskModel(
    val taskId: String = "",
    val taskTitle: String = "",
    val subjectName: String = "",
    val description: String = "",
    val assignTo: String = "",
    val assignByProfessor: String = "",
    val deadlineDate: String = "",
    val creationDate: String = "",
    val progress: TaskStatus = TaskStatus.CLOSED,
)

enum class TaskStatus(val key: String) {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    CLOSED("CLOSED");

    companion object {
        fun fromKey(key: String): TaskStatus {
            return entries.firstOrNull { it.key == key }
                ?: throw IllegalArgumentException("Unknown TaskStatus key: $key")
        }
    }
}
