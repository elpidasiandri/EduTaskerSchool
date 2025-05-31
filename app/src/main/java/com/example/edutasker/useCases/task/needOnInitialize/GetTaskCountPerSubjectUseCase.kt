package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.model.SubjectTaskCount
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class GetTaskCountPerSubjectUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(): List<SubjectTaskCount> {
        return repo.getTaskCountPerSubject()
    }
}
