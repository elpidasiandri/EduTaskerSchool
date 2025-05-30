package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.relations.TaskWithStudent
import com.example.edutasker.repo.IDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksOfAllStudents(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(): Flow<List<TaskWithStudent>> {
        return repo.getAllTasksOfAllStudents()
    }
}
