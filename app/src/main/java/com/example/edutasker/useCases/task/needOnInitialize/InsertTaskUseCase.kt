package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.entities.TaskEntity
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class InsertTaskUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(task: TaskEntity) {
        repo.insertTask(task)
    }
}
