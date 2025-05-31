package com.example.edutasker.useCases.student

import com.example.edutasker.model.StudentPreviewAsListModel
import com.example.edutasker.repo.relationsDatabase.IDatabaseRepository

class GetNameIdsAndImageOfStudentUseCase(
    private val databaseRepository: IDatabaseRepository,
) {
    suspend operator fun invoke(): List<StudentPreviewAsListModel> {
        return databaseRepository.getNameIdAndImageOfStudents( )
    }
}