package com.example.edutasker.useCases.task.updateByProfessor

import com.example.edutasker.model.UpdateTaskByProfessorModel
import com.example.edutasker.repo.IDatabaseRepository

class UpdateTaskByProfessorUseCase (
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(taskInfo: UpdateTaskByProfessorModel) {
        return repo.updateTaskByProfessor(taskInfo)
    }
}