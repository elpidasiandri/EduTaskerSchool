package com.example.edutasker.useCases.professor

import com.example.edutasker.entities.ProfessorEntity
import com.example.edutasker.repo.IDatabaseRepository

class GetProfessorByEmailUseCase(
    private val databaseRepository: IDatabaseRepository,
) {
    suspend operator fun invoke(email: String): ProfessorEntity? {
        return databaseRepository.getProfessorByEmail(email)
    }
}