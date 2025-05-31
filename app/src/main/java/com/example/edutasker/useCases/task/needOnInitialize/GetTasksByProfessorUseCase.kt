package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class GetTasksByProfessorUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(professorId: String): List<TaskEntity> {
        return repo.getTasksByProfessor(professorId)
    }
}
