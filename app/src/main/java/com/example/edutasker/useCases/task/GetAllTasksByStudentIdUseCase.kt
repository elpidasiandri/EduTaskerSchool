package com.example.edutasker.useCases.task

import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksByStudentIdUseCase(
    private val repo: ITaskDatabaseRepository,
) {
    suspend operator fun invoke(studentId: String): Flow<List<TaskEntity?>?> {
        return repo.getAllTaskByStudentId(studentId)
    }
}

