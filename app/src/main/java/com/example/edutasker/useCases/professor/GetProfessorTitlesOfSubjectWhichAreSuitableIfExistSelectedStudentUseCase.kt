package com.example.edutasker.useCases.professor

import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.professorDatabase.IProfessorDatabaseRepository

class GetProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase(private val repo: IProfessorDatabaseRepository) {
    suspend operator fun invoke(selectedStudent: StudentPreviewAsListModel? = null): List<String> {
        return repo.getProfessorSubjects(
            CurrentUser.getCurrentUserId(),
            selectedStudent
        )
    }
}