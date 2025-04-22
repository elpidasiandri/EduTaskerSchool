package com.example.edutasker.useCases.task

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.IDatabaseRepository

class GetTasksForSubjectUseCase(
    private val repo: IDatabaseRepository
) {
    suspend operator fun invoke(subjectName: String): List<TaskEntity> {
        return repo.getTasksForSubject(subjectName)
    }
}
