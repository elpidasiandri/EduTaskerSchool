package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class GetTasksForSubjectUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(subjectName: String): List<TaskEntity> {
        return repo.getTasksForSubject(subjectName)
    }
}
