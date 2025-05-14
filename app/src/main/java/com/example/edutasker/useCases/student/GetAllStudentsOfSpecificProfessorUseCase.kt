package com.example.edutasker.useCases.student

import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.IDatabaseRepository

class GetAllStudentsOfSpecificProfessorUseCase(
    private val databaseRepository: IDatabaseRepository,
) {
    suspend operator fun invoke(specificSubject: String? = null): List<StudentPreviewAsListModel> {
        return databaseRepository.getAllStudentsOfProfessorSubjects(
            CurrentUser.userId ?: "",
            specificSubject = specificSubject
        )
    }
}