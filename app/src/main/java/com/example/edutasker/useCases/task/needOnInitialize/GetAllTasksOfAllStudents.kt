package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.db.entities.relations.TaskWithStudent
import com.example.edutasker.repo.relationsDatabase.IDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksOfAllStudents(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(): Flow<List<TaskWithStudent>> {
        return repo.getAllTasksOfAllStudents()
    }
}
