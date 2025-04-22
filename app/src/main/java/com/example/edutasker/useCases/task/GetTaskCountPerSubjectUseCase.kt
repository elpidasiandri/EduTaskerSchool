package com.example.edutasker.useCases.task

import com.example.edutasker.dao.SubjectTaskCount
import com.example.edutasker.repo.IDatabaseRepository

class GetTaskCountPerSubjectUseCase(
    private val repo: IDatabaseRepository
) {
    suspend operator fun invoke(): List<SubjectTaskCount> {
        return repo.getTaskCountPerSubject()
    }
}
