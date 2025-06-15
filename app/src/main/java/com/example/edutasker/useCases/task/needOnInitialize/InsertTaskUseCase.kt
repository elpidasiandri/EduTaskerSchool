package com.example.edutasker.useCases.task.needOnInitialize

import com.example.edutasker.db.entities.TaskEntity
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class InsertTaskUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(task: TaskEntity): String {
        return repo.insertTask(task)
    }
}