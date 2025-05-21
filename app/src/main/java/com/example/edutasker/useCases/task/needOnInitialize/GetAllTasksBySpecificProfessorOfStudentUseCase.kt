package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.repo.IDatabaseRepository

class GetAllTasksBySpecificProfessorOfStudentUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(studentId: String): List<TasksWithStudentImageModel> {
        return repo.getAllTasksBySpecificProfessorOfStudent(studentId)
    }
}