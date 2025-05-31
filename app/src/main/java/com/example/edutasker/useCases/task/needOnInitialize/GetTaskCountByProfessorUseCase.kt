package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class GetTaskCountByProfessorUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(profId: String): Int {
        return repo.getTaskCountByProfessor(profId)
    }
}
