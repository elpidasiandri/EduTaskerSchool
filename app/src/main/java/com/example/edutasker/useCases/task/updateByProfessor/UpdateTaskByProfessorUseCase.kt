package com.example.edutasker.useCases.task.updateByProfessor

import com.example.edutasker.model.UpdateTaskByProfessorModel
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class UpdateTaskByProfessorUseCase (
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(taskInfo: UpdateTaskByProfessorModel) {
        return repo.updateTaskByProfessor(taskInfo)
    }
}