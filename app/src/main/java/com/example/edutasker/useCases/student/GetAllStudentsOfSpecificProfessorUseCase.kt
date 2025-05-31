package com.example.edutasker.useCases.student

import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository

class GetAllStudentsOfSpecificProfessorUseCase(
    private val repo: IProfessorDatabaseRepository,
) {
    suspend operator fun invoke(specificSubject: String? = null): List<StudentPreviewAsListModel> {
        return repo.getAllStudentsOfProfessorSubjects(
            CurrentUser.getCurrentUserId(),
            specificSubject = specificSubject
        )
    }
}