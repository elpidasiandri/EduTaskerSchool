package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.db.entities.relations.TaskWithStudent
import com.example.edutasker.repo.relationsDatabase.IDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetAllTasksBySpecificProfessorOfStudentUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(studentId: String): Flow<List<TaskWithStudent>> {
        return repo.getAllTasksBySpecificProfessorOfStudent(studentId)
    }
}