package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.IDatabaseRepository

class GetTasksByProfessorUseCase(
    private val repo: IDatabaseRepository
) {
    suspend operator fun invoke(professorId: String): List<TaskEntity> {
        return repo.getTasksByProfessor(professorId)
    }
}
