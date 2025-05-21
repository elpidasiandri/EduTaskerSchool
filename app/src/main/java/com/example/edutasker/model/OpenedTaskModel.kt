package com.example.edutasker.model

data class OpenedTaskModel(
    val taskInfo: TaskModel,
    val studentBasic: StudentPreviewAsListModel,
    val professorBasic: ProfessorBasicModel,
)