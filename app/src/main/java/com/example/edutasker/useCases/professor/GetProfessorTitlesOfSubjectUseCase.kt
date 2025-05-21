package com.example.edutasker.useCases.professor

import com.example.edutasker.mockData.CurrentUser
import com.example.edutasker.repo.IDatabaseRepository

class GetProfessorTitlesOfSubjectUseCase(private val databaseRepository: IDatabaseRepository) {
    suspend operator fun invoke(): List<String> {
        return databaseRepository.getProfessorSubjects(CurrentUser.getCurrentUserId())
    }
}