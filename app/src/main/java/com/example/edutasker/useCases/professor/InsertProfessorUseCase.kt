package com.example.edutasker.useCases.professor

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.repo.IDatabaseRepository

class InsertProfessorUseCase(
    private val databaseRepository: IDatabaseRepository
) {
    suspend operator fun invoke(professor: ProfessorEntity) {
        databaseRepository.insertProfessor(professor)
    }
}
