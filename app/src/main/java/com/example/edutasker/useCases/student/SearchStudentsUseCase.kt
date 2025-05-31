package com.example.edutasker.useCases.student

import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.relationsDatabase.IDatabaseRepository

class SearchStudentsUseCase(
    private val databaseRepository: IDatabaseRepository,
) {
    suspend operator fun invoke(keyword: String): List<StudentPreviewAsListModel> {
        return databaseRepository.searchStudents(keyword)
    }
}