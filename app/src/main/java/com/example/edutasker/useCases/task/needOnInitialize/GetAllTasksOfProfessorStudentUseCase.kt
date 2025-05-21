package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.model.TasksWithStudentImageModel
import com.example.edutasker.repo.IDatabaseRepository

class GetAllTasksOfProfessorStudentUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(): List<TasksWithStudentImageModel> {
        return repo.getAllTasksOfProfessorStudent()
    }
}
