package com.example.edutasker.useCases.task

import com.example.edutasker.model.OpenedTaskModel
import com.example.edutasker.repo.relationsDatabase.IDatabaseRepository

class GetAllInfoOfTaskAndBasicOfStudentAndProfessorUseCase(
    private val repo: IDatabaseRepository,
) {
    suspend operator fun invoke(taskId:String): OpenedTaskModel {
        return repo.getAllInfoAboutTaskAndBasicOfStudentAndProfessor(taskId)
    }
}

