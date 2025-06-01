package com.example.edutasker.useCases.task.updateByStudent

import com.example.edutasker.model.UpdateTaskByProfessorModel
import com.example.edutasker.repo.taskDatabase.ITaskDatabaseRepository

class UpdateTaskByStudentUseCase(
    private val repo: ITaskDatabaseRepository
) {
    suspend operator fun invoke(taskInfo: UpdateTaskByProfessorModel) {
        return repo.updateTaskByStudent(taskInfo.taskId, taskInfo.progress)
    }
}