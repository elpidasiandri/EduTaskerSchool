package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.IDatabaseRepository

class InsertTaskUseCase(
    private val repo: IDatabaseRepository
) {
    suspend operator fun invoke(task: TaskEntity) {
        repo.insertTask(task)
    }
}
