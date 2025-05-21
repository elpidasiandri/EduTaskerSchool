package com.example.edutasker.useCases.student

import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.IDatabaseRepository

class SearchProfessorStudentsHavingSpecificSubjectUseCase(
    private val databaseRepository: IDatabaseRepository,
) {
    suspend operator fun invoke(
        keyword: String,
        specificSubject: String? = null,
    ): List<StudentPreviewAsListModel> {
        return databaseRepository.searchAllStudentsOfProfessorSubjects(
            keyword = keyword,
            idProfessor = CurrentUser.getCurrentUserId(),
            specificSubject = specificSubject
        )
    }
}