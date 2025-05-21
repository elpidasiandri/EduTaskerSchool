package com.example.edutasker.useCases.task

import com.example.edutasker.model.OpenedTask
import com.example.edutasker.repo.IDatabaseRepository

class GetAllInfoOfTaskAndBasicOfStudentsUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(taskId:String): OpenedTask {
        return repo.getAllInfoAboutTaskAndBasicOfStudent(taskId)
    }
}

