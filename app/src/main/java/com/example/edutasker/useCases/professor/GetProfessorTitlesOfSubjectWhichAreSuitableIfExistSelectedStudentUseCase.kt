package com.example.edutasker.useCases.professor

import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.IDatabaseRepository

class GetProfessorTitlesOfSubjectWhichAreSuitableIfExistSelectedStudentUseCase(private val databaseRepository: IDatabaseRepository) {
    suspend operator fun invoke(selectedStudent: StudentPreviewAsListModel?=null): List<String> {
        return databaseRepository.getProfessorSubjects(
            CurrentUser.getCurrentUserId(),
            selectedStudent
        )
    }
}