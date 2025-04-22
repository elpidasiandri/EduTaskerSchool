package com.example.edutasker.useCases.task

import com.example.edutasker.repo.IDatabaseRepository

class AssignTaskToStudentsUseCase(
    private val repo: IDatabaseRepository
) {
    suspend operator fun invoke(taskId: String, studentIds: List<String>) {
        repo.assignTaskToStudents(taskId, studentIds)
    }
}
